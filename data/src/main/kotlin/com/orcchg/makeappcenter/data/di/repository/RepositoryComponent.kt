package com.orcchg.makeappcenter.data.di.repository

import com.orcchg.makeappcenter.data.di.viewmodel.ViewModelComponent
import com.orcchg.makeappcenter.data.di.viewmodel.ViewModelModule
import com.orcchg.makeappcenter.data.repository.cart.CartRepository
import com.orcchg.makeappcenter.data.repository.shopify.checkout.CheckoutRepository
import com.orcchg.makeappcenter.data.repository.shopify.product.ProductRepository
import com.orcchg.makeappcenter.data.repository.shopify.redirect.RedirectRepository
import com.orcchg.makeappcenter.data.repository.shopify.webpage.WebPageRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RepositoryModule::class))
interface RepositoryComponent {

    fun plus(viewModelModule: ViewModelModule): ViewModelComponent

    fun cartRepository(): CartRepository
    fun checkoutRepository(): CheckoutRepository
    fun productRepository(): ProductRepository
    fun redirectRepository(): RedirectRepository
    fun webPageRepository(): WebPageRepository
}
