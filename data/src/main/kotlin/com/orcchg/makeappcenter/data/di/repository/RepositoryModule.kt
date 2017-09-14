package com.orcchg.makeappcenter.data.di.repository

import com.orcchg.makeappcenter.data.di.local.LocalModule
import com.orcchg.makeappcenter.data.di.remote.CloudModule
import dagger.Module

@Module(includes = arrayOf(CloudModule::class, LocalModule::class))
class RepositoryModule
