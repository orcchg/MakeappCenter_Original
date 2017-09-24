package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "checkouts")
data class Checkout(@PrimaryKey var id: String = "", var webUrl: String = "")
