package com.orcchg.makeappcenter.data.source.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

/**
 * Analyzes network response, broadcasts an event (through [EventBus]) in case of [NetworkEvent],
 * with optional response body.
 */
class ResponseErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            Timber.v("Sending event through bus: network error")
            EventBus.getDefault().post(NetworkError(response.code()))
        }
        return response
    }
}
