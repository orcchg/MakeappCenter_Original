package com.orcchg.makeappcenter.app.view.home

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
import com.orcchg.makeappcenter.app.common.adapter.collection.ProductCollectionsListAdapter
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.viewmodel.product.ProductViewModel

class HomeFragment : BaseFragment() {

    @BindView(R.id.rv_collections) lateinit var collections: RecyclerView
    @BindView(R.id.progressbar) lateinit var progressbar: View

    private lateinit var vm: ProductViewModel

    private lateinit var adapter: ProductCollectionsListAdapter

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
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
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        ButterKnife.bind(this, rootView)

        adapter = ProductCollectionsListAdapter()
        collections.layoutManager = LinearLayoutManager(context)
        collections.isNestedScrollingEnabled = false
        collections.adapter = adapter
//        OverScrollDecoratorHelper.setUpOverScroll(collections, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)

        return rootView
    }

    override fun onStart() {
        super.onStart()
        vm.collections()
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe({
//            it.forEach {
//                Timber.i("Collection[${it.products.size}]: ${it.title}")
//                it.products.forEach { Timber.d("Product: ${it.title}") }
//            }
            adapter.items = it
        })

//        val bigSetsCollectionId = "Z2lkOi8vc2hvcGlmeS9Db2xsZWN0aW9uLzQ0NzM1Njk0OQ"
//        val dinosCollectionId = "Z2lkOi8vc2hvcGlmeS9Db2xsZWN0aW9uLzQ0NzU4MTA3Nw"
//        vm.productsForCollection(bigSetsCollectionId).subscribe({ Timber.i("BIG SETS: ${it.joinToString(transform = { it.title })}") })
//        vm.productsForCollection(dinosCollectionId).subscribe({ Timber.i("DINOS: ${it.joinToString(transform = { it.title })}") })
    }

    /* View */
    // --------------------------------------------------------------------------------------------
    private fun showLoading(isVisible: Boolean) {
        progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
