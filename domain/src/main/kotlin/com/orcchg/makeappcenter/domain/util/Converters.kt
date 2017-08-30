package com.orcchg.makeappcenter.domain.util

import android.arch.persistence.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun fromString(str: String) = BigDecimal(str)

    @TypeConverter
    fun toString(bd: BigDecimal) = bd.toString()
}
