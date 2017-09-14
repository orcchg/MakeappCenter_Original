package com.orcchg.makeappcenter.data.source.remote.shopify.webpage

import com.orcchg.makeappcenter.data.repository.Rx
import com.orcchg.makeappcenter.data.source.remote.shopify.api.AdminRestAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebPageCloud @Inject constructor(private val restAdapter: AdminRestAdapter) {

    fun pages() = restAdapter.pages().compose(Rx.flowableTransformer())
}
