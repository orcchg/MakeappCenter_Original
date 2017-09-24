package com.orcchg.makeappcenter.data.viewmodel.shopify.checkout

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.shopify.checkout.CheckoutRepository
import com.orcchg.makeappcenter.domain.model.Product

class CheckoutViewModel(private val repository: CheckoutRepository) : ViewModel() {

    fun createCheckout(products: Collection<Product>) = repository.createCheckout(products)
}
