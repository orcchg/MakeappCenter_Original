package com.orcchg.makeappcenter.data.di.remote

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.CustomTypeAdapter
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.orcchg.makeappcenter.data.BuildConfig
import com.orcchg.makeappcenter.data.R
import com.orcchg.makeappcenter.data.graphql.type.CustomType
import com.orcchg.makeappcenter.data.source.remote.network.RequestHeaderInterceptor
import com.orcchg.makeappcenter.data.source.remote.network.ResponseErrorInterceptor
import com.shopify.buy3.GraphClient
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class CloudModule(private val context: Context) {

    /* Common */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    /* Apollo */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        val shopUrl = context.getString(R.string.shopify_domain)
        return ApolloClient.builder()
                .okHttpClient(okHttpClient)
                .serverUrl(HttpUrl.parse("https://$shopUrl/api/graphql")!!)
                .httpCacheStore(DiskLruHttpCacheStore(context.cacheDir, 1000 * 1024))
                .addCustomTypeAdapter(CustomType.MONEY, object : CustomTypeAdapter<BigDecimal> {
                    override fun decode(value: String): BigDecimal { return BigDecimal(value) }
                    override fun encode(value: BigDecimal): String { return value.toString() }
                })
                .build()
    }

    /* OkHttp */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    internal fun provideHttpLoggingInterceptor(): LoggingInterceptor =
        LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build()

    @Provides @Singleton
    internal fun provideRequestHeaderInterceptor(): RequestHeaderInterceptor {
        val guestToken = context.getString(R.string.guest_token)
        return RequestHeaderInterceptor(guestToken)
    }

    @Provides @Singleton
    internal fun provideResponseErrorInterceptor(): ResponseErrorInterceptor {
        return ResponseErrorInterceptor()
    }

    /**
     * @see https://futurestud.io/tutorials/retrofit-2-manage-request-headers-in-okhttp-interceptor
     */
    @Provides @Singleton
    internal fun provideOkHttpClient(errorInterceptor: ResponseErrorInterceptor,
                                     headerInterceptor: RequestHeaderInterceptor,
                                     logInterceptor: LoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
//                .addInterceptor(errorInterceptor)
//                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)  // logging interceptor must be initialized last to log properly
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    /* Retrofit */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
    }

    /* Shopify */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    fun provideShopifyGraphClient(okHttpClient: OkHttpClient): GraphClient {
        val accessToken = context.getString(R.string.shopify_api_key)
        val shopDomain = context.getString(R.string.shopify_domain)
        return GraphClient.builder(context)
                .accessToken(accessToken)
                .shopDomain(shopDomain)
                .httpClient(okHttpClient)
                .build()
    }
}
