package com.orcchg.makeappcenter.data.util

import com.apollographql.apollo.ApolloMutationCall
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.orcchg.makeappcenter.domain.util.Util
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.exceptions.Exceptions

object RxUtil {

    fun <T> rxApolloQueryCall(call: ApolloQueryCall<T>): Single<T> {
        return Single.create<Response<T>>({ emitter ->
            emitter.setCancellable(call::cancel)
            try {
                emitter.onSuccess(call.execute())
            } catch (e: Exception) {
                Exceptions.throwIfFatal(e)
                emitter.onError(e)
            }
        }).compose(queryResponseDataTransformer())
    }

    fun <T> rxApolloMutationCall(call: ApolloMutationCall<T>): Single<T> {
        return Single.create<Response<T>>({ emitter ->
            emitter.setCancellable(call::cancel)
            try {
                emitter.onSuccess(call.execute())
            } catch (e: Exception) {
                Exceptions.throwIfFatal(e)
                emitter.onError(e)
            }
        }).compose(queryResponseDataTransformer())
    }

    private fun <T> queryResponseDataTransformer(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { it ->
            it.flatMap { response ->
                if (response.errors().isEmpty()) {
                    Single.just(response.data())
                } else {
                    val errorMessage = Util.fold(StringBuilder(), response.errors(),
                            { builder, error -> builder.append(error.message()).append("\n") })
                    Single.error(RuntimeException(errorMessage.toString()))
                }
              }
        }
    }
}
