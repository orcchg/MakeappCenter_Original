package com.orcchg.makeappcenter.data.viewmodel

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.CartRepository

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    fun cartSize() = repository.cartSize()

    fun productsInCart() = repository.productsInCart()
}
