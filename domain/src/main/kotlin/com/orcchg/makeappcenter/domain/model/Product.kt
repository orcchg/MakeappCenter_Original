package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.shopify.buy3.Storefront
import java.math.BigDecimal

@Entity(tableName = "products")
data class Product(@PrimaryKey var id: String = "", var coverUrl: String = "", var description: String = "",
                   var price: BigDecimal = BigDecimal.ZERO, var title: String = "") {

    companion object {
        fun from(product: Storefront.Product): Product {
            val imageEdges = product.images.edges
            val coverUrl = if (!imageEdges.isEmpty()) imageEdges[0].node.src else ""
            val description = product.description
            val variantEdges = product.variants.edges
            val price = if (!variantEdges.isEmpty()) variantEdges[0].node.price else BigDecimal.ZERO
            return Product(id = product.id.toString(), coverUrl = coverUrl, description = description,
                    price = price, title = product.title)
        }
    }
}
