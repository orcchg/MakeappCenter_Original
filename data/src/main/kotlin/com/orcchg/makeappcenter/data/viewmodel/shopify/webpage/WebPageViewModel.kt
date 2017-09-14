package com.orcchg.makeappcenter.data.viewmodel.shopify.webpage

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.shopify.webpage.WebPageRepository

class WebPageViewModel(private val repository: WebPageRepository) : ViewModel() {

    fun page(id: Long) = repository.page(id)
    fun pages() = repository.pages()
}
