package com.orcchg.makeappcenter.data.repository.product

import com.orcchg.makeappcenter.data.source.local.product.ProductDao
import com.orcchg.makeappcenter.domain.model.Product
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(productDao: ProductDao) {

    fun products(): Flowable<List<Product>> {
        //
    }
}
