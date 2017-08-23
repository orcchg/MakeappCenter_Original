package com.orcchg.makeappcenter.app.view.main

import android.os.Bundle
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.view.base.BaseActivity

class MainActivity : BaseActivity() {

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
