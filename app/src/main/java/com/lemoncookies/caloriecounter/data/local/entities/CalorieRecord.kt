package com.lemoncookies.caloriecounter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lemoncookies.caloriecounter.data.local.utils.Converters

@Entity(tableName = "calorie_table")
@TypeConverters(Converters::class)
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