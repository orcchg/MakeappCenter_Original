package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.shopify.buy3.Storefront
import java.math.BigDecimal

@Entity(tableName = "products")
data class Product(@PrimaryKey @ColumnInfo(name = "id") var id: String = "",
                   @ColumnInfo(name = "coverUrl") var coverUrl: String = "",
                   @ColumnInfo(name = "price") var price: BigDecimal = BigDecimal.ZERO,
                   @ColumnInfo(name = "title") var title: String = "") {

    companion object {
        fun from(product: Storefront.Product): Product {
            val imageEdges = product.images.edges
            val coverUrl = if (!imageEdges.isEmpty()) imageEdges[0].node.src else ""
            val variantEdges = product.variants.edges
            val price = if (!variantEdges.isEmpty()) variantEdges[0].node.price else BigDecimal.ZERO
            return Product(id = product.id.toString(), coverUrl = coverUrl, price = price, title = product.title)
        }
    }
}
