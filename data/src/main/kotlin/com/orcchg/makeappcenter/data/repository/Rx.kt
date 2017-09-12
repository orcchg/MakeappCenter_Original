package com.orcchg.makeappcenter.data.repository

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Maybe
import io.reactivex.MaybeTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

object Rx {

    fun <T> flowableTransformer(): FlowableTransformer<T, T> {
        return FlowableTransformer { observable ->
            observable.compose(analyzeNetworkError()).compose(applySchedulers())
        }
    }

    fun <T> maybeTransformer(): MaybeTransformer<T, T> {
        return MaybeTransformer { observable ->
            observable.compose(analyzeNetworkErrorMaybe()).compose(applySchedulersMaybe())
        }
    }

    /* Internal */
    // --------------------------------------------------------------------------------------------
    private fun <T> applySchedulers(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun <T> applySchedulersMaybe(): MaybeTransformer<T, T> {
        return MaybeTransformer {
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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

    private fun <T> analyzeNetworkErrorMaybe(): MaybeTransformer<T, T> {
        return MaybeTransformer { observable ->
            observable.onErrorResumeNext(Function {
                Maybe.error(it)
            })
        }
    }
}
