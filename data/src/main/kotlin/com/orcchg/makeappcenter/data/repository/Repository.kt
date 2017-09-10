package com.orcchg.makeappcenter.data.repository

abstract class Repository {

    fun onCreate() {
//        EventBus.getDefault().register(this)
    }

    fun onDestroy() {
//        EventBus.getDefault().unregister(this)
    }
}
