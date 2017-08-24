package com.orcchg.makeappcenter.app.view.splash

import android.os.Bundle
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.view.base.BaseActivity
import rx.Observable
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    companion object {
        const val SPLASH_DELAY = 750L
    }

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        // wait a bit and then proceed to the next screen
        Observable.just(10)
                .delay(SPLASH_DELAY, TimeUnit.MILLISECONDS)
                .doOnCompleted(this::afterSplash)
                .subscribe()
    }

    /* Internal */
    // --------------------------------------------------------------------------------------------
    private fun afterSplash() {
        navigator.openMainScreen(this)
        finish()
    }
}
