package com.orcchg.makeappcenter.data.repository.product

import com.orcchg.makeappcenter.data.repository.Repository
import com.orcchg.makeappcenter.data.repository.Rx
import com.orcchg.makeappcenter.data.source.local.product.ProductDao
import com.orcchg.makeappcenter.data.source.remote.shopify.product.ProductCloud
import com.orcchg.makeappcenter.domain.model.Product
import com.orcchg.makeappcenter.domain.model.ProductCollection
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val productCloud: ProductCloud,
                                            private val productDao: ProductDao)
    : Repository() {

    fun collection(collectionId: String): Flowable<ProductCollection> {
        // TODO: from local
        return productCloud.collection(collectionId)
                .compose(Rx.flowableTransformer())
    }

    fun collections(): Flowable<List<ProductCollection>> {
        // TODO: from local
        return productCloud.collections()
                .compose(Rx.flowableTransformer())
    }

    fun products(): Flowable<List<Product>> {
        // TODO: from local
        return productCloud.products()
                .compose(Rx.flowableTransformer())
    }

    fun productsForCollection(collectionId: String): Flowable<List<Product>> {
        // TODO: from local
        return productCloud.productsForCollection(collectionId)
                .compose(Rx.flowableTransformer())
    }
}
