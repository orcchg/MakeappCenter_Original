package com.orcchg.makeappcenter.data.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orcchg.makeappcenter.data.repository.CartRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartViewModelFactory @Inject constructor(private val repository: CartRepository)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
