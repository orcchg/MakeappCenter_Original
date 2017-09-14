package com.orcchg.makeappcenter.data.di.viewmodel

import com.orcchg.makeappcenter.data.viewmodel.CartViewModelFactory
import com.orcchg.makeappcenter.data.viewmodel.shopify.product.ProductViewModelFactory
import com.orcchg.makeappcenter.data.viewmodel.shopify.redirect.RedirectViewModelFactory
import com.orcchg.makeappcenter.data.viewmodel.shopify.webpage.WebPageViewModelFactory
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(ViewModelModule::class))
interface ViewModelComponent {

    /* Product */
    // --------------------------------------------------------------------------------------------
    fun productFactory(): ProductViewModelFactory

    /* Products in cart */
    // ------------------------------------------
    fun cartFactory(): CartViewModelFactory

    /* Redirect */
    // --------------------------------------------------------------------------------------------
    fun redirectFactory(): RedirectViewModelFactory

    /* Web Page */
    // --------------------------------------------------------------------------------------------
    fun webPageFactory(): WebPageViewModelFactory
}
