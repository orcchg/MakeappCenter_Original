package com.orcchg.makeappcenter.data.repository.shopify.webpage

import com.orcchg.makeappcenter.data.repository.Repository
import com.orcchg.makeappcenter.data.source.remote.shopify.webpage.WebPageCloud
import com.orcchg.makeappcenter.domain.model.WebPage
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebPageRepository @Inject constructor(private val webPageCloud: WebPageCloud) : Repository() {

    fun pages(): Flowable<WebPage> {
        // TODO: from local
        return webPageCloud.pages().flatMapIterable { it.pages }
    }
}
