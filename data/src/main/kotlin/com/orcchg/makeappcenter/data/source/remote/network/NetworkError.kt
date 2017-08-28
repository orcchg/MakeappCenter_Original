package com.orcchg.makeappcenter.data.source.remote.network

import org.greenrobot.eventbus.EventBus

/**
 * Event for [EventBus] that signals for some kind of network event.
 */
sealed class NetworkEvent {

    override fun toString(): String {
        return javaClass.simpleName
    }
}

data class NetworkError(val code: Int)
