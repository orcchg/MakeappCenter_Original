package com.orcchg.makeappcenter.app.view.profile

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.viewmodel.shopify.redirect.RedirectViewModel
import com.orcchg.makeappcenter.data.viewmodel.shopify.webpage.WebPageViewModel
import timber.log.Timber

class ProfileFragment : BaseFragment() {

    @BindView(R.id.tv_webpage) lateinit var webPageText: TextView

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    private lateinit var redirectVm: RedirectViewModel
    private lateinit var webPageVm: WebPageViewModel

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        redirectVm = ViewModelProviders.of(this, viewModelComponent.redirectFactory()).get(RedirectViewModel::class.java)
        webPageVm = ViewModelProviders.of(this, viewModelComponent.webPageFactory()).get(WebPageViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        ButterKnife.bind(this, rootView)
        return rootView
    }

    override fun onStart() {
        super.onStart()
        redirectVm.redirects().subscribe { Timber.v("REDIRECT: $it") }
//        webPageVm.pages().subscribe { Timber.v("WEB PAGE: $it") }

        // TODO: show specific page - CONTACT US, id = 212484358
        webPageVm.page(212484358L).subscribe {
            Timber.v("WEB PAGE: $it")
            webPageText.text = Html.fromHtml(it.body_html)
        }
    }
}
