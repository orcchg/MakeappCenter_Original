package com.orcchg.makeappcenter.data.di.remote

import android.content.Context
import com.orcchg.makeappcenter.data.R
import com.shopify.buy3.GraphClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CloudModule(private val context: Context) {

    /* Shopify */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    fun provideShopifyGraphClient(): GraphClient {
        val accessToken = context.getString(R.string.shopify_api_key)
        val shopDomain = context.getString(R.string.shopify_domain)
        return GraphClient.builder(context)
                .accessToken(accessToken)
                .shopDomain(shopDomain)
                .build()
    }
}
