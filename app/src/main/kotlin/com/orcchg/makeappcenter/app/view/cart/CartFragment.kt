package com.orcchg.makeappcenter.app.view.cart

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.adapter.product.ProductsInCartAdapter
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.viewmodel.CartViewModel
import com.orcchg.makeappcenter.data.viewmodel.shopify.checkout.CheckoutViewModel
import com.orcchg.makeappcenter.domain.model.Product

class CartFragment : BaseFragment() {

    @BindView(R.id.progressbar) lateinit var progressbar: View
    @BindView(R.id.rv_products) lateinit var products: RecyclerView
    @BindView(R.id.tv_total_price) lateinit var totalPrice: TextView

    @OnClick(R.id.btn_checkout)
    internal fun onCheckoutClick() {
        // TODO: handle results
        checkoutVm.createCheckout(productsInCart).subscribe()
    }

    companion object {
        fun newInstance(): CartFragment {
            return CartFragment()
        }
    }

    private lateinit var cartVm: CartViewModel
    private lateinit var checkoutVm: CheckoutViewModel

    private lateinit var adapter: ProductsInCartAdapter

    // TODO: move to coordinator?
    private lateinit var productsInCart: Collection<Product>

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartVm = ViewModelProviders.of(this, viewModelComponent.cartFactory()).get(CartViewModel::class.java)
        checkoutVm = ViewModelProviders.of(this, viewModelComponent.checkoutFactory()).get(CheckoutViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_cart, container, false)
        ButterKnife.bind(this, rootView)

        adapter = ProductsInCartAdapter()
        products.layoutManager = LinearLayoutManager(context)
        products.adapter = adapter

        return rootView
    }

    override fun onStart() {
        super.onStart()
        cartVm.productsInCart()
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe {
                    productsInCart = it
                    adapter.items = it
                    totalPrice.text = if (it.isEmpty()) "0"
                                      else String.format("%s", it.map { it.price }
                                                    .reduce { sum, item -> sum + item })
                }
    }

    /* View */
    // --------------------------------------------------------------------------------------------
    private fun showLoading(isVisible: Boolean) {
        progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
