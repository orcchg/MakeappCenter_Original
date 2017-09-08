package com.orcchg.makeappcenter.data.viewmodel.product

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.product.ProductRepository
import com.orcchg.makeappcenter.domain.model.Product
import io.reactivex.Flowable

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    fun collection(collectionId: String) = repository.collection(collectionId)
    fun collections() = repository.collections()

    fun products(): Flowable<List<Product>> = repository.products()
    fun productsForCollection(collectionId: String) = repository.productsForCollection(collectionId)
}
