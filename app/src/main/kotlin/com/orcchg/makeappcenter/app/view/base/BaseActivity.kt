package com.orcchg.makeappcenter.app.view.base

import android.support.v7.app.AppCompatActivity
import com.orcchg.makeappcenter.app.navigation.Navigator
import com.orcchg.makeappcenter.data.di.viewmodel.ViewModelComponent

abstract class BaseActivity : AppCompatActivity() {

    protected val navigator = Navigator()

    lateinit var viewModelComponent: ViewModelComponent
}
