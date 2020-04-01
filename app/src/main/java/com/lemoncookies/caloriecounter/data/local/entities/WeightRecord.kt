package com.lemoncookies.caloriecounter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lemoncookies.caloriecounter.data.local.utils.Constants.TABLE_WEIGHT
import com.lemoncookies.caloriecounter.data.local.utils.Constants.WEIGHT_DATE
import com.lemoncookies.caloriecounter.data.local.utils.Constants.WEIGHT_ID
import com.lemoncookies.caloriecounter.data.local.utils.Constants.WEIGHT_VALUE

@Entity(tableName = TABLE_WEIGHT)
data class WeightRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = WEIGHT_ID)
    var id: Long = 0,

    @ColumnInfo(name = WEIGHT_VALUE)
    var weight: Float = 0f,

    @ColumnInfo(name = WEIGHT_DATE)
    var date: Long = 0
)