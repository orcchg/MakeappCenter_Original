package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "redirects")
data class Redirect(@PrimaryKey var id: String = "", var path: String = "", var target: String = "")
