package com.orcchg.makeappcenter.data.viewmodel.shopify.checkout

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orcchg.makeappcenter.data.repository.shopify.checkout.CheckoutRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckoutViewModelFactory @Inject constructor(private val repository: CheckoutRepository)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckoutViewModel::class.java)) {
            return CheckoutViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
