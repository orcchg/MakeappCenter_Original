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

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this, viewModelComponent.productFactory()).get(ProductViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        ButterKnife.bind(this, rootView)
        return rootView
    }
}
