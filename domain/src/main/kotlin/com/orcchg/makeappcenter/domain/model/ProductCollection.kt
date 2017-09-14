package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.shopify.buy3.Storefront

@Entity(tableName = "products")
data class ProductCollection(@PrimaryKey var id: String = "", var coverUrl: String = "",
                             var products: List<Product>, var title: String = "") {

    companion object {
        fun from(collection: Storefront.Collection): ProductCollection {
            val products = arrayListOf<Product>()
            collection.products.edges.forEach { products.add(Product.from(it.node)) }
            return ProductCollection(id = collection.id.toString(),
                    coverUrl = collection.image.src, products = products, title = collection.title)
        }
    }
}
