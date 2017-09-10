package com.orcchg.makeappcenter.data.viewmodel.product

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.product.CartRepository

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    fun productsInCart() = repository.productsInCart()
}
