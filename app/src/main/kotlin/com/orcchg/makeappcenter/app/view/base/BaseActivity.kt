package com.orcchg.makeappcenter.app.view.base

import android.support.v7.app.AppCompatActivity
import com.orcchg.makeappcenter.app.navigation.Navigator

abstract class BaseActivity : AppCompatActivity() {

    protected val navigator = Navigator()
}
