package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "redirects")
data class Redirect(@PrimaryKey @ColumnInfo(name = "id") var id: String = "",
                    @ColumnInfo(name = "path") var path: String = "",
                    @ColumnInfo(name = "target") var target: String = "")
