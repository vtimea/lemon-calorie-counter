package com.lemoncookies.caloriecounter.data.local.dao

import androidx.room.*
import com.lemoncookies.caloriecounter.data.local.entities.WeightRecord
import org.joda.time.DateTime

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecord(record: WeightRecord)

    @Delete
    fun removeRecord(record: WeightRecord)

    @Query("SELECT * FROM weight_table")
    fun getAll(): List<WeightRecord>

    @Query("SELECT * FROM weight_table WHERE date BETWEEN :dayStart AND :dayEnd")
    fun getByDate(dayStart: DateTime, dayEnd: DateTime): List<WeightRecord>

    @Query("SELECT * FROM weight_table WHERE date BETWEEN :fromDate AND :toDate")
    fun getBetweenDates(fromDate: DateTime, toDate: DateTime): List<WeightRecord>

    @Query("SELECT * FROM weight_table WHERE id==id")
    fun getById(id: Int): WeightRecord
}