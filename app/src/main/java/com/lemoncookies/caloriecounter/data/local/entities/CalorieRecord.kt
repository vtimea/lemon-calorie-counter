package com.lemoncookies.caloriecounter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import org.joda.time.DateTime

@Entity(tableName = "calorie_table")
data class CalorieRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "calories")
    var calories: Int,

    @ColumnInfo(name = "date")
    var date: Long
)

@TypeConverter
fun toDate(dateLong: Long): DateTime {
    return DateTime(dateLong)
}

@TypeConverter
fun fromDate(date: DateTime): Long {
    return date.millis;
}