package com.orcchg.makeappcenter.data.source.remote.shopify.webpage

import com.orcchg.makeappcenter.data.source.remote.shopify.api.AdminRestAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebPageCloud @Inject constructor(private val restAdapter: AdminRestAdapter) {

    fun page(id: Long) = restAdapter.page("$id.json")

    fun pages() = restAdapter.pages()
}
