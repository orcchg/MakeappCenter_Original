package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "webPages")
data class WebPage(@PrimaryKey @ColumnInfo(name = "id") var id: Int = 0,
                   @ColumnInfo(name = "title") var title: String = "")
