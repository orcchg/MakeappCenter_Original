package com.orcchg.makeappcenter.data.repository

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

object Rx {

    fun completableTransformer(): CompletableTransformer {
        return CompletableTransformer { observable ->
            observable.compose(analyzeNetworkErrorCompletable()).compose(applySchedulersCompletable())
        }
    }

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
    private fun applySchedulersCompletable(): CompletableTransformer {
        return CompletableTransformer {
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

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

    // ------------------------------------------
    private fun analyzeNetworkErrorCompletable(): CompletableTransformer {
        return CompletableTransformer { observable ->
            observable.onErrorResumeNext({ Completable.error(it) })
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
