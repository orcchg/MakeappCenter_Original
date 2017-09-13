package com.orcchg.makeappcenter.data.di.remote.shopify

import com.orcchg.makeappcenter.data.di.remote.CloudModule
import com.orcchg.makeappcenter.data.source.remote.shopify.api.AdminRestAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = arrayOf(CloudModule::class))
class AdminCloudModule {
    // TODO: distinguish for different API later, provide each own okHttp client and interceptors
    // TODO: in order to differentiate headers

    @Provides @Singleton
    internal fun provideAdminRestAdapter(retrofit: Retrofit.Builder): AdminRestAdapter {
        return retrofit.baseUrl(AdminRestAdapter.ENDPOINT).build()
                .create(AdminRestAdapter::class.java)
    }
}
