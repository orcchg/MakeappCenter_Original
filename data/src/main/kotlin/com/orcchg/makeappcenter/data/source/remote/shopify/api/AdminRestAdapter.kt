package com.orcchg.makeappcenter.data.source.remote.shopify.api

import com.orcchg.makeappcenter.domain.model.RedirectBundle
import com.orcchg.makeappcenter.domain.model.WebPageBundle
import io.reactivex.Flowable
import retrofit2.http.GET

interface AdminRestAdapter {

    companion object {
        // TODO: get endpoint host from resources
        const val ENDPOINT = "https://food-for-space.myshopify.com/admin/"
    }

    @GET("pages.json")
    fun pages(): Flowable<WebPageBundle>

    @GET("redirects.json")
    fun redirects(): Flowable<RedirectBundle>
}
