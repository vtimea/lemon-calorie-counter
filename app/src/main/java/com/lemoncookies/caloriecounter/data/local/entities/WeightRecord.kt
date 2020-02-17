package com.lemoncookies.caloriecounter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lemoncookies.caloriecounter.data.local.utils.Converters

@Entity(tableName = "weight_table")
@TypeConverters(Converters::class)
data class WeightRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "calories")
    var weight: Int,

    @ColumnInfo(name = "date")
    var date: Long
)