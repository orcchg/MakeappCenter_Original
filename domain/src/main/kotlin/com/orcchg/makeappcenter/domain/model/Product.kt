package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.shopify.buy3.Storefront
import java.math.BigDecimal

@Entity(tableName = "products")
data class Product(@PrimaryKey var id: String = "",
                   var coverUrl: String = "",
                   var description: String = "",
                   var price: BigDecimal = BigDecimal.ZERO,
                   var title: String = "",
                   var variantId: String = "") {

    companion object {
        fun from(product: Storefront.Product): Product {
            val imageEdges = product.images.edges
            val coverUrl = if (!imageEdges.isEmpty()) imageEdges[0].node.src else ""

            val variantEdges = product.variants.edges
            var price = BigDecimal.ZERO
            var variantId = ""
            if (!variantEdges.isEmpty()) {
                val variantNode = variantEdges[0].node
                price = variantNode.price
                variantId = variantNode.id.toString()
            }

            return Product(id = product.id.toString(), coverUrl = coverUrl, description = product.description,
                    price = price, title = product.title, variantId = variantId)
        }
    }
}
