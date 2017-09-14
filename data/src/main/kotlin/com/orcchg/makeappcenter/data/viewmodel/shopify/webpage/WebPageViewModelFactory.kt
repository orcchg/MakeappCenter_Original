package com.orcchg.makeappcenter.data.viewmodel.shopify.webpage

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orcchg.makeappcenter.data.repository.shopify.webpage.WebPageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebPageViewModelFactory @Inject constructor(private val repository: WebPageRepository)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WebPageViewModel::class.java)) {
            return WebPageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
