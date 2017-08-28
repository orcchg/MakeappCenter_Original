package com.orcchg.makeappcenter.app.view.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.viewmodel.product.ProductViewModel
import timber.log.Timber

class HomeFragment : BaseFragment() {

    private lateinit var vm: ProductViewModel

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
        return rootView
    }

    override fun onStart() {
        super.onStart()
        vm.collections().subscribe({ Timber.i("COLLECTIONS: ${it.joinToString()}") })
        vm.products().subscribe({ Timber.i("PRODUCTS: ${it.joinToString(transform = { it.title })}") })

//        val bigSetsCollectionId = "Z2lkOi8vc2hvcGlmeS9Db2xsZWN0aW9uLzQ0NzM1Njk0OQ"
//        val dinosCollectionId = "Z2lkOi8vc2hvcGlmeS9Db2xsZWN0aW9uLzQ0NzU4MTA3Nw"
//        vm.productsForCollection(bigSetsCollectionId).subscribe({ Timber.i("BIG SETS: ${it.joinToString(transform = { it.title })}") })
//        vm.productsForCollection(dinosCollectionId).subscribe({ Timber.i("DINOS: ${it.joinToString(transform = { it.title })}") })
    }
}
