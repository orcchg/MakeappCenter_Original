package com.orcchg.makeappcenter.data.source.remote.shopify.api

import com.orcchg.makeappcenter.domain.model.RedirectBundle
import io.reactivex.Flowable
import retrofit2.http.GET

interface AdminRestAdapter {

    companion object {
        // TODO: get endpoint host from resources
        const val ENDPOINT = "https://food-for-space.myshopify.com/admin/"
    }

    @GET("redirects.json")
    fun redirects(): Flowable<RedirectBundle>
}
