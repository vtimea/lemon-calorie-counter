package com.lemoncookies.caloriecounter.data.local.db

import android.app.Application
import com.lemoncookies.caloriecounter.data.local.dao.WeightDao
import com.lemoncookies.caloriecounter.data.local.entities.WeightRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import kotlin.coroutines.CoroutineContext


class WeightRepository(application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var weightDao: WeightDao?

    init {
        val db = LocalDatabase.getDatabase(application)
        weightDao = db?.weightDao()
    }

    fun getRecords() = weightDao?.getAll()

    suspend fun addRecord(record: WeightRecord) {
        withContext(Dispatchers.IO) {
            launch {
                weightDao?.addRecord(record)
            }
        }
    }

    suspend fun removeRecord(record: WeightRecord) {
        withContext(Dispatchers.IO) {
            launch {
                weightDao?.removeRecord(record)
            }
        }
    }

    fun getByDate(day: DateTime): List<WeightRecord> {
        val dayStart = day.withMillisOfDay(0)
        val dayEnd = day.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59)
        return weightDao?.getByDate(dayStart, dayEnd) ?: listOf()
    }

    fun getBetweenDates(startDate: DateTime, endDate: DateTime) =
        weightDao?.getBetweenDates(startDate, endDate) ?: listOf()

    fun getById(id: Int) = weightDao?.getById(id)
}