package com.orcchg.makeappcenter.data.source.remote.shopify

import com.orcchg.makeappcenter.data.graphql.CollectionPageWithProductsQuery
import com.orcchg.makeappcenter.domain.model.ProductCollection

object Converters {

    /* Products */
    // --------------------------------------------------------------------------------------------
    fun convert(edges: List<CollectionPageWithProductsQuery.Edge>): List<ProductCollection> {
        val list = mutableListOf<ProductCollection>()
//        edges.forEach { list.add(ProductCollection(it.collection.id, it.collection.title)) }
        return list
    }
}
