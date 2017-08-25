package com.orcchg.makeappcenter.app.di.application

import android.content.Context
import com.orcchg.makeappcenter.app.MainApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MainApplication) {

    /* Context */
    // ------------------------------------------
    @Provides @Singleton
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    /* Execution */
    // ------------------------------------------
    @Named("Executor") @Provides @Singleton
    fun provideExecuteScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Named("Observer") @Provides @Singleton
    fun provideObserveScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
