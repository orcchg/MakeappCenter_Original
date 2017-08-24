package com.orcchg.makeappcenter.app.navigation

import android.content.Context
import com.orcchg.makeappcenter.app.view.main.MainActivity

class Navigator {

    fun openMainScreen(context: Context) {
        context.startActivity(MainActivity.getCallingIntent(context))
    }
}
