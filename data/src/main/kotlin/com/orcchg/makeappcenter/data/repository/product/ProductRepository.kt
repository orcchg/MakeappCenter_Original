package com.orcchg.makeappcenter.data.repository.product

import com.orcchg.makeappcenter.data.source.local.product.ProductDao
import com.orcchg.makeappcenter.data.source.remote.shopify.product.ProductCloud
import com.orcchg.makeappcenter.domain.model.Product
import com.orcchg.makeappcenter.domain.model.ProductCollection
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val productCloud: ProductCloud,
                                            private val productDao: ProductDao) {

    fun collections(): Flowable<List<ProductCollection>> {
        // TODO: from local
        return productCloud.collections()
    }

    fun products(): Flowable<List<Product>> {
        // TODO: from local
        return productCloud.products().subscribeOn(Schedulers.io())
    }

    fun productsForCollection(collectionId: String): Flowable<List<Product>> {
        // TODO: from local
        return productCloud.productsForCollection(collectionId)
    }
}
