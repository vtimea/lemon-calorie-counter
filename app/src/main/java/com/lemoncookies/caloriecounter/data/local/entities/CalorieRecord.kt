package com.lemoncookies.caloriecounter.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_DATE
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_ID
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_NAME
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_VALUE
import com.lemoncookies.caloriecounter.data.local.utils.Constants.TABLE_CALORIES
import com.lemoncookies.caloriecounter.data.local.utils.Converters

@Entity(tableName = TABLE_CALORIES)
@TypeConverters(Converters::class)
data class CalorieRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CALORIES_ID)
    var id: Int,

    @ColumnInfo(name = CALORIES_NAME)
    var name: String,

    @ColumnInfo(name = CALORIES_VALUE)
    var calories: Int,

    @ColumnInfo(name = CALORIES_DATE)
    var date: Long
)