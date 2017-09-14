package com.orcchg.makeappcenter.data.source.remote.shopify.redirect

import com.orcchg.makeappcenter.data.source.remote.shopify.api.AdminRestAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedirectCloud @Inject constructor(private val restAdapter: AdminRestAdapter) {

    fun redirects() = restAdapter.redirects()
}
