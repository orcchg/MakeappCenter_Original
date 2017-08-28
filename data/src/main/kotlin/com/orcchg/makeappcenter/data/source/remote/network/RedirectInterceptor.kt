package com.orcchg.makeappcenter.data.source.remote.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * Extracts redirect url from the response.
 *
 * @see https://stackoverflow.com/questions/38966028/how-to-get-okhttp3-redirected-url
 */
class RedirectInterceptor : Interceptor {

    private var redirectUrl: HttpUrl? = null

    fun getRedirectUrlOrThrow(): String {
        Timber.i("Accessing redirect url: ${if (redirectUrl == null) null else redirectUrl}")
        return redirectUrl!!.toString()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        redirectUrl = response.request().url()
        Timber.i("Intercepted redirect url: $redirectUrl")
        return response
    }
}
