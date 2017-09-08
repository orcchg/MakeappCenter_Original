package com.orcchg.makeappcenter.data.repository

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

object RepositoryUtility {

    fun <T> applySchedulers(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> mainTransformer(): FlowableTransformer<T, T> {
        return FlowableTransformer { observable ->
            observable.compose(analyzeNetworkError()).compose(applySchedulers())
        }
    }

    private fun <T> analyzeNetworkError(): FlowableTransformer<T, T> {
        return FlowableTransformer { observable ->
            observable.onErrorResumeNext(Function {
                Flowable.error(it)
            })
//                if (it is HttpException) {
//                    // cache to prevent missing responseBody if stream was closed
//                    val responseBody = it.response().errorBody()?.data()
//                    val apiError = networkManager.getApiError(responseBody)
//                    Flowable.error(NetworkManager.ApiErrorException(apiError))
//                } else {
//                    Flowable.error(it)
//                }
            }
        }
}
