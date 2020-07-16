package com.lemoncookies.caloriecounter.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_DATE
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_ID
import com.lemoncookies.caloriecounter.data.local.utils.Constants.CALORIES_NAME
import com.lemoncookies.caloriecounter.data.local.utils.Constants.TABLE_CALORIES
import org.joda.time.DateTime

@Dao
interface CalorieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecord(record: CalorieRecord)

    @Delete
    fun removeRecord(record: CalorieRecord)

    @Query("DELETE FROM $TABLE_CALORIES WHERE id=:id")
    fun removeRecord(id: Long)

    @Query("SELECT * FROM $TABLE_CALORIES")
    fun getAll(): LiveData<List<CalorieRecord>>

    @Query("SELECT * FROM $TABLE_CALORIES WHERE $CALORIES_NAME LIKE '%' || :name || '%'")
    fun getByName(name: String): List<CalorieRecord>

    @Query("SELECT * FROM $TABLE_CALORIES WHERE $CALORIES_DATE BETWEEN :dayStart AND :dayEnd")
    fun getByDate(dayStart: DateTime, dayEnd: DateTime): LiveData<List<CalorieRecord>>

    @Query("SELECT * FROM $TABLE_CALORIES WHERE $CALORIES_DATE BETWEEN :fromDate AND :toDate")
    fun getBetweenDates(fromDate: DateTime, toDate: DateTime): List<CalorieRecord>

    @Query("SELECT * FROM $TABLE_CALORIES WHERE $CALORIES_ID==:id")
    fun getById(id: Long): CalorieRecord

    @Query("DELETE FROM $TABLE_CALORIES")
    fun clearData()
}