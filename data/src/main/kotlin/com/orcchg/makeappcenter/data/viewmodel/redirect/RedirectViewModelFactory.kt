package com.orcchg.makeappcenter.data.viewmodel.redirect

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orcchg.makeappcenter.data.repository.redirect.RedirectRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedirectViewModelFactory @Inject constructor(private val repository: RedirectRepository)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RedirectViewModel::class.java)) {
            return RedirectViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
