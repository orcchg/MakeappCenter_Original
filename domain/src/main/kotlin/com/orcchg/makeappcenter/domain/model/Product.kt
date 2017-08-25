package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
data class Product(@PrimaryKey @ColumnInfo(name = "id") val id: Int)
