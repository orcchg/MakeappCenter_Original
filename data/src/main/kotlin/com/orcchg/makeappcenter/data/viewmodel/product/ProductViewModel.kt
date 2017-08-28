package com.orcchg.makeappcenter.data.viewmodel.product

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.product.ProductRepository

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    fun collections() = repository.collections()

    fun products() = repository.products()
    fun productsForCollection(collectionId: String) = repository.productsForCollection(collectionId)
}
