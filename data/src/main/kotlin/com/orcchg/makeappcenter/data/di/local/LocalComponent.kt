package com.orcchg.makeappcenter.data.di.local

import com.orcchg.makeappcenter.data.di.viewmodel.ViewModelComponent
import com.orcchg.makeappcenter.data.di.viewmodel.ViewModelModule
import com.orcchg.makeappcenter.data.source.local.product.ProductDao
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(LocalModule::class))
interface LocalComponent {

    fun plus(viewModelModule: ViewModelModule): ViewModelComponent

    /* Product */
    // --------------------------------------------------------------------------------------------
    fun productDao(): ProductDao
}
