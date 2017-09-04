package com.orcchg.makeappcenter.app.view.location

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.adapter.product.ProductsGridAdapter
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.viewmodel.product.ProductViewModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class LocationFragment : BaseFragment() {

    @BindView(R.id.rv_products) lateinit var products: RecyclerView
    @BindView(R.id.progressbar) lateinit var progressbar: View

    private lateinit var vm: ProductViewModel

    private lateinit var adapter: ProductsGridAdapter

    companion object {
        fun newInstance(): LocationFragment {
            return LocationFragment()
        }
    }

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProviders.of(this, viewModelComponent.productFactory()).get(ProductViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_location, container, false)
        ButterKnife.bind(this, rootView)

        adapter = ProductsGridAdapter()
        products.layoutManager = GridLayoutManager(context, 2)
        products.adapter = adapter
        OverScrollDecoratorHelper.setUpOverScroll(products, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)

        return rootView
    }

    override fun onStart() {
        super.onStart()
        vm.products()
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
