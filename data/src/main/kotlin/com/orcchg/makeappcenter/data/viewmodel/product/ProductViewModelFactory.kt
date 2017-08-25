package com.orcchg.makeappcenter.data.viewmodel.product

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orcchg.makeappcenter.data.repository.product.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductViewModelFactory @Inject constructor(private val repository: ProductRepository)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
