package com.orcchg.makeappcenter.data.source.remote.shopify.checkout

import com.orcchg.makeappcenter.domain.model.Checkout
import com.orcchg.makeappcenter.domain.model.Product
import com.shopify.buy3.*
import com.shopify.graphql.support.ID
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckoutCloud @Inject constructor(private val shopifyClient: GraphClient) {

    fun checkout(products: Collection<Product>): Flowable<Checkout> {
        // TODO: specify quantity
        val input = Storefront.CheckoutCreateInput()
                .setLineItems(products.map { Storefront.CheckoutLineItemInput(1, ID(it.variantId)) })

        val query = Storefront.mutation {
            it.checkoutCreate(input, {
                it.checkout {
                    it.webUrl()
                }
                .userErrors {
                    it.field().message()
                }
            })
        }

        return Flowable.create<Checkout>({ emitter ->
            shopifyClient.mutateGraph(query).enqueue(object : GraphCall.Callback<Storefront.Mutation> {
                override fun onResponse(response: GraphResponse<Storefront.Mutation>) {
                    val data = response.data()
                    if (data != null) {
                        val errors = data.checkoutCreate.userErrors
                        if (!errors.isEmpty()) {
                            errors.forEach {
                                Timber.e("Checkout failed with error: ${it.message}")
                            }
                            emitter.onError(IllegalStateException())
                        } else {
                            val id = data.checkoutCreate.checkout.id.toString()
                            val webUrl = data.checkoutCreate.checkout.webUrl
                            emitter.onNext(Checkout(id, webUrl))
                            emitter.onComplete()
                        }
                    } else {
                        Timber.w("Checkout data is null!")
                        emitter.onError(NullPointerException())
                    }
                }

                override fun onFailure(error: GraphError) {
                    emitter.onError(error)
                }
            })
        }, BackpressureStrategy.BUFFER)
    }
}
