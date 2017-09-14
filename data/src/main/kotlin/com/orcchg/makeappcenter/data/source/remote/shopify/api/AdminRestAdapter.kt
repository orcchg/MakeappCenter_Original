package com.orcchg.makeappcenter.data.source.remote.shopify.api

import com.orcchg.makeappcenter.domain.model.RedirectBundle
import com.orcchg.makeappcenter.domain.model.WebPageBundle
import com.orcchg.makeappcenter.domain.model.WebPageNested
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AdminRestAdapter {

    companion object {
        // TODO: get endpoint host from resources
        const val ENDPOINT = "https://food-for-space.myshopify.com/admin/"
    }

    /* Redirect */
    // --------------------------------------------------------------------------------------------
    @GET("redirects.json")
    fun redirects(): Flowable<RedirectBundle>

    /* Web Page */
    // --------------------------------------------------------------------------------------------
    @GET("pages/{id}")
    fun page(@Path("id") id: String): Flowable<WebPageNested>

    @GET("pages.json")
    fun pages(): Flowable<WebPageBundle>
}
