package com.orcchg.makeappcenter.data.di.viewmodel

import com.orcchg.makeappcenter.data.viewmodel.product.CartViewModelFactory
import com.orcchg.makeappcenter.data.viewmodel.product.ProductViewModelFactory
import com.orcchg.makeappcenter.data.viewmodel.redirect.RedirectViewModelFactory
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
}
