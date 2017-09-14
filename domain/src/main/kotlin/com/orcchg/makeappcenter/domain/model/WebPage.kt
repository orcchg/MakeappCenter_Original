package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "webPages")
data class WebPage(@PrimaryKey var id: Long = 0, var body_html: String = "", var title: String = "")
