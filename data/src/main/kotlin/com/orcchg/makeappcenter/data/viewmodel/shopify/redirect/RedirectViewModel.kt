package com.orcchg.makeappcenter.data.viewmodel.shopify.redirect

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.shopify.redirect.RedirectRepository

class RedirectViewModel(private val repository: RedirectRepository) : ViewModel() {

    fun redirects() = repository.redirects()
}
