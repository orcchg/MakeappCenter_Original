package com.orcchg.makeappcenter.data.source.remote

import com.orcchg.makeappcenter.domain.model.Product
import com.shopify.buy3.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class ShopifyCloud(private val client: GraphClient) {

    /* Product */
    // --------------------------------------------------------------------------------------------
    fun products(): Flowable<List<Product>> {
        val query = Storefront.query {
            it.shop {
                it.products(5, {
                    it.edges {
                        it.node {
                            it.title()
                              .description()
                            }
                        }
                })
            }
        }

        return Flowable.create<List<Product>>({ emitter ->
            client.queryGraph(query).enqueue(object : GraphCall.Callback<Storefront.QueryRoot> {
                override fun onResponse(response: GraphResponse<Storefront.QueryRoot>) {
                    val products = mutableListOf<Product>()
                    val collectionEdges = response.data()?.shop?.collections?.edges
                    collectionEdges?.forEach {
                        val productEdges = it.node.products.edges
                        productEdges.forEach { products.add(it.node) }
                    }
                    emitter.onNext(products)
                    emitter.onComplete()
                }

                override fun onFailure(error: GraphError) {
                    emitter.onError(error)
                }
            })
        }, BackpressureStrategy.BUFFER)
    }
}
