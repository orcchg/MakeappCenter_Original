package com.orcchg.makeappcenter.data.di.viewmodel

import com.orcchg.makeappcenter.data.viewmodel.product.ProductViewModelFactory
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(ViewModelModule::class))
interface ViewModelComponent {

    /* Product */
    // --------------------------------------------------------------------------------------------
    fun productFactory(): ProductViewModelFactory
}
