package com.orcchg.makeappcenter.data.viewmodel.redirect

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.redirect.RedirectRepository

class RedirectViewModel(private val repository: RedirectRepository) : ViewModel() {

    fun redirects() = repository.redirects()
}
