package com.orcchg.makeappcenter.app.view.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.view.base.BaseFragment

class LocationFragment : BaseFragment() {

    companion object {
        fun newInstance(): LocationFragment {
            return LocationFragment()
        }
    }

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_location, container, false)
        ButterKnife.bind(this, rootView)
        return rootView
    }
}
