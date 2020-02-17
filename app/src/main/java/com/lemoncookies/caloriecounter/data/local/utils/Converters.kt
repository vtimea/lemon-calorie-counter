package com.lemoncookies.caloriecounter.data.local.utils

import androidx.room.TypeConverter
import org.joda.time.DateTime

class Converters {
    @TypeConverter
    fun toDate(dateLong: Long): DateTime {
        return DateTime(dateLong)
    }

    @TypeConverter
    fun fromDate(date: DateTime): Long {
        return date.millis;
    }
}