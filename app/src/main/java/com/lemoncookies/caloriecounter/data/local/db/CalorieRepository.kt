package com.lemoncookies.caloriecounter.data.local.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.lemoncookies.caloriecounter.data.local.dao.CalorieDao
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import kotlin.coroutines.CoroutineContext


class CalorieRepository(application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var calorieDao: CalorieDao

    init {
        val db = LocalDatabase.getDatabase(application)
        calorieDao = db.calorieDao()
    }

    fun getRecords() = calorieDao.getAll()

    fun addRecord(record: CalorieRecord) {
        launch {
            addRecordBG(record)
        }
    }

    fun removeRecord(record: CalorieRecord) {
        launch {
            removeRecordBG(record)
        }
    }

    fun removeRecord(id: Long) {
        launch {
            removeRecordBG(id)
        }
    }

    fun getByName(name: String) = calorieDao.getByName(name)

    fun getByDate(day: DateTime): LiveData<List<CalorieRecord>> {
        val dayStart = day.withMillisOfDay(0)
        val dayEnd = day.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59)
        return calorieDao.getByDate(dayStart, dayEnd)
    }

    fun getBetweenDates(startDate: DateTime, endDate: DateTime) =
        calorieDao.getBetweenDates(startDate, endDate)

    fun getById(id: Long) = calorieDao.getById(id)

    fun clearData() {
        calorieDao.clearData()
    }

    private suspend fun addRecordBG(record: CalorieRecord) {
        withContext(Dispatchers.IO) {
            launch {
                calorieDao.addRecord(record)
            }
        }
    }

    private suspend fun removeRecordBG(record: CalorieRecord) {
        withContext(Dispatchers.IO) {
            launch {
                calorieDao.removeRecord(record)
            }
        }
    }

    private suspend fun removeRecordBG(id: Long) {
        withContext(Dispatchers.IO) {
            launch {
                calorieDao.removeRecord(id)
            }
        }
    }
}