package com.orcchg.makeappcenter.app.di.application

import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun context(): Context
}
