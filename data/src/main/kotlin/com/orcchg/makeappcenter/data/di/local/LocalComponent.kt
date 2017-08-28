package com.orcchg.makeappcenter.data.di.local

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(LocalModule::class))
interface LocalComponent
