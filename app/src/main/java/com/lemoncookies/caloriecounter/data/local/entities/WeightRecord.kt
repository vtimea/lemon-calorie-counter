package com.lemoncookies.caloriecounter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lemoncookies.caloriecounter.data.local.utils.Constants.TABLE_WEIGHT
import com.lemoncookies.caloriecounter.data.local.utils.Constants.WEIGHT_DATE
import com.lemoncookies.caloriecounter.data.local.utils.Constants.WEIGHT_ID
import com.lemoncookies.caloriecounter.data.local.utils.Constants.WEIGHT_VALUE
import com.lemoncookies.caloriecounter.data.local.utils.Converters

@Entity(tableName = TABLE_WEIGHT)
@TypeConverters(Converters::class)
data class WeightRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = WEIGHT_ID)
    var id: Int,

    @ColumnInfo(name = WEIGHT_VALUE)
    var weight: Int,

    @ColumnInfo(name = WEIGHT_DATE)
    var date: Long
)