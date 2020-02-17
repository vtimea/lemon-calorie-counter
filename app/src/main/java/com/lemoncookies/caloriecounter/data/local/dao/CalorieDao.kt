package com.lemoncookies.caloriecounter.data.local.dao

import androidx.room.*
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import org.joda.time.DateTime

@Dao
interface CalorieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecord(record: CalorieRecord)

    @Delete
    fun removeRecord(record: CalorieRecord)

    @Query("SELECT * FROM calorie_table")
    fun getAll(): List<CalorieRecord>

    @Query("SELECT * FROM calorie_table WHERE name LIKE '%' || :name || '%'")
    fun getByName(name: String): List<CalorieRecord>

    @Query("SELECT * FROM calorie_table WHERE date BETWEEN :dayStart AND :dayEnd")
    fun getByDate(dayStart: DateTime, dayEnd: DateTime): List<CalorieRecord>

    @Query("SELECT * FROM calorie_table WHERE date BETWEEN :fromDate AND :toDate")
    fun getBetweenDates(fromDate: DateTime, toDate: DateTime): List<CalorieRecord>

    @Query("SELECT * FROM calorie_table WHERE id==id")
    fun getById(id: Int): CalorieRecord

}