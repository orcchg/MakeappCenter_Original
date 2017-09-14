package com.orcchg.makeappcenter.data.viewmodel.shopify.product

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.shopify.product.ProductRepository

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    fun collection(collectionId: String) = repository.collection(collectionId)
    fun collections() = repository.collections()

    fun product(productId: String) = repository.product(productId)
    fun products() = repository.products()
    fun productsForCollection(collectionId: String) = repository.productsForCollection(collectionId)
}
