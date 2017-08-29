package com.orcchg.makeappcenter.data.source.remote.shopify.product

import com.apollographql.apollo.ApolloClient
import com.orcchg.makeappcenter.data.graphql.CollectionPageWithProductsQuery
import com.orcchg.makeappcenter.data.graphql.type.CollectionSortKeys
import com.orcchg.makeappcenter.data.source.remote.shopify.Converters
import com.orcchg.makeappcenter.data.util.RxUtil
import com.orcchg.makeappcenter.domain.model.Product
import com.orcchg.makeappcenter.domain.model.ProductCollection
import com.shopify.buy3.*
import com.shopify.graphql.support.ID
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCloud @Inject constructor(private val apolloClient: ApolloClient,
                                       private val shopifyClient: GraphClient) {

    // ------------------------------------------
    fun collectionsApollo(): Single<List<ProductCollection>> {
        val query = CollectionPageWithProductsQuery.builder()
                .perPage(10)
                .collectionSortKey(CollectionSortKeys.TITLE)
                .build()

        return RxUtil.rxApolloQueryCall(apolloClient.query(query))
                .map { it.get() }
                .map { it.shop }
                .map { it.collectionConnection }
                .map { it.edges }
                .subscribeOn(Schedulers.io())
                .map(Converters::convert)
    }

    fun collections(): Flowable<List<ProductCollection>> {
        val query = Storefront.query {
            it.shop {
                it.collections(20, {
                    it.edges {
                        it.node {
                            it.title()
                        }
                    }
                })
            }
        }

        return Flowable.create<List<ProductCollection>>({ emitter ->
            shopifyClient.queryGraph(query).enqueue(object : GraphCall.Callback<Storefront.QueryRoot> {
                override fun onResponse(response: GraphResponse<Storefront.QueryRoot>) {
                    val productCollections = mutableListOf<ProductCollection>()
                    val collectionEdges = response.data()?.shop?.collections?.edges
                    collectionEdges?.forEach {
                        productCollections.add(ProductCollection.from(it.node))
                    }
                    emitter.onNext(productCollections)
                    emitter.onComplete()
                }

                override fun onFailure(error: GraphError) {
                    emitter.onError(error)
                }
            })
        }, BackpressureStrategy.BUFFER)
    }

    // ------------------------------------------
    fun products(): Flowable<List<Product>> {
        val query = Storefront.query {
            it.shop {
                it.collections(20, {
                    it.edges {
                        it.node {
                            it.title()
                               .products(20, {
                                   it.edges {
                                       it.node {
                                           it.title()
                                             .description()
                                       }
                                   }
                               })
                        }
                    }
                })
            }
        }

        return Flowable.create<List<Product>>({ emitter ->
            shopifyClient.queryGraph(query).enqueue(object : GraphCall.Callback<Storefront.QueryRoot> {
                override fun onResponse(response: GraphResponse<Storefront.QueryRoot>) {
                    val products = mutableListOf<Product>()
                    val collectionEdges = response.data()?.shop?.collections?.edges
                    collectionEdges?.forEach {
                        val productEdges = it.node.products.edges
                        productEdges.forEach { products.add(Product.from(it.node)) }
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

    // ------------------------------------------
    fun productsForCollection(collectionId: String): Flowable<List<Product>> {
        val query = Storefront.query {
            it.node(ID(collectionId), {
                it.onCollection {
                    it.products(10, {
                        it.edges {
                            it.node {
                                it.title()
                                  .description()
                            }
                        }
                    })
                }
            })
        }

        return Flowable.create<List<Product>>({ emitter ->
            shopifyClient.queryGraph(query).enqueue(object : GraphCall.Callback<Storefront.QueryRoot> {
                override fun onResponse(response: GraphResponse<Storefront.QueryRoot>) {
                    val products = mutableListOf<Product>()
                    val productNodes = response.data()?.nodes
                    productNodes?.forEach {
                        products.add(Product.from(it as Storefront.Product))
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
