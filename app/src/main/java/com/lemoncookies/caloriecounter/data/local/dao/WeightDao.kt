package com.lemoncookies.caloriecounter.data.local.dao

import androidx.room.*
import com.lemoncookies.caloriecounter.data.local.entities.WeightRecord
import com.lemoncookies.caloriecounter.data.local.utils.Constants.TABLE_WEIGHT
import com.lemoncookies.caloriecounter.data.local.utils.Constants.WEIGHT_DATE
import com.lemoncookies.caloriecounter.data.local.utils.Constants.WEIGHT_ID
import org.joda.time.DateTime

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecord(record: WeightRecord)

    @Delete
    fun removeRecord(record: WeightRecord)

    @Query("SELECT * FROM $TABLE_WEIGHT")
    fun getAll(): List<WeightRecord>

    @Query("SELECT * FROM $TABLE_WEIGHT WHERE $WEIGHT_DATE BETWEEN :dayStart AND :dayEnd")
    fun getByDate(dayStart: DateTime, dayEnd: DateTime): List<WeightRecord>

    @Query("SELECT * FROM $TABLE_WEIGHT WHERE $WEIGHT_DATE BETWEEN :fromDate AND :toDate")
    fun getBetweenDates(fromDate: DateTime, toDate: DateTime): List<WeightRecord>

    @Query("SELECT * FROM $TABLE_WEIGHT WHERE $WEIGHT_ID==:id")
    fun getById(id: Long): WeightRecord
}