package com.orcchg.makeappcenter.data.di.remote

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(CloudModule::class))
interface CloudComponent
