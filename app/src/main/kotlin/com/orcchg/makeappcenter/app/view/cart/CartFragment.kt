package com.orcchg.makeappcenter.app.view.cart

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.adapter.product.ProductsInCartAdapter
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.viewmodel.product.CartViewModel

class CartFragment : BaseFragment() {

    @BindView(R.id.rv_products) lateinit var products: RecyclerView
    @BindView(R.id.progressbar) lateinit var progressbar: View

    companion object {
        fun newInstance(): CartFragment {
            return CartFragment()
        }
    }

    private lateinit var vm: CartViewModel

    private lateinit var adapter: ProductsInCartAdapter

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(this, viewModelComponent.cartFactory()).get(CartViewModel::class.java)
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
        vm.productsInCart()
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe { adapter.items = it }
    }

    /* View */
    // --------------------------------------------------------------------------------------------
    private fun showLoading(isVisible: Boolean) {
        progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
