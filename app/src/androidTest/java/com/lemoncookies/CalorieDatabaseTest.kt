package com.lemoncookies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.lemoncookies.caloriecounter.data.local.dao.CalorieDao
import com.lemoncookies.caloriecounter.data.local.db.LocalDatabase
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CalorieDatabaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var sleepDao: CalorieDao
    private lateinit var db: LocalDatabase

    @Before
    fun initDatabase() {
        LocalDatabase.TEST_MODE = true
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = LocalDatabase.getDatabase(context)
        sleepDao = db.calorieDao()
    }

    @Test
    fun removeRecord() {
        val id: Long = 1
        val record = CalorieRecord(id, "Apple", 85, System.currentTimeMillis())
        sleepDao.addRecord(record)
        Assert.assertEquals(record, sleepDao.getById(id))
        sleepDao.removeRecord(record)
        Assert.assertEquals(null, sleepDao.getById(id))
    }

    @Test
    fun removeById() {
        val id: Long = 1
        val record = CalorieRecord(id, "Apple", 85, System.currentTimeMillis())
        sleepDao.addRecord(record)
        Assert.assertEquals(record, sleepDao.getById(id))
        sleepDao.removeRecord(id)
        Assert.assertEquals(null, sleepDao.getById(id))
    }

    @Test
    fun getAll() {
        sleepDao.addRecord(CalorieRecord(1, "Apple", 85, System.currentTimeMillis()))
        sleepDao.addRecord(CalorieRecord(2, "Apple", 85, System.currentTimeMillis()))
        sleepDao.getAll().observeForever {
            Assert.assertEquals(2, it.size)
        }
    }

    @Test
    fun getById() {
        val id: Long = 1
        val record = CalorieRecord(id, "Apple", 85, System.currentTimeMillis())
        sleepDao.addRecord(record)
        val retrieved = sleepDao.getById(id)
        Assert.assertEquals(record, retrieved)
    }

    @Test
    fun getByName_full() {
        sleepDao.addRecord(CalorieRecord(1, "record1", 85, System.currentTimeMillis()))
        sleepDao.addRecord(CalorieRecord(2, "record2", 85, System.currentTimeMillis()))
        sleepDao.addRecord(CalorieRecord(3, "Apple", 85, System.currentTimeMillis()))
        sleepDao.addRecord(CalorieRecord(4, "Apple", 85, System.currentTimeMillis()))
        val records = sleepDao.getByName("record1")
        Assert.assertEquals(1, records.size)
    }

    @Test
    fun getByName_partial() {
        sleepDao.addRecord(CalorieRecord(1, "record1", 85, System.currentTimeMillis()))
        sleepDao.addRecord(CalorieRecord(2, "record2", 85, System.currentTimeMillis()))
        sleepDao.addRecord(CalorieRecord(3, "Apple", 85, System.currentTimeMillis()))
        sleepDao.addRecord(CalorieRecord(4, "Apple", 85, System.currentTimeMillis()))
        val records = sleepDao.getByName("record")
        Assert.assertEquals(2, records.size)
    }

    @Test
    fun getByDate() {
        sleepDao.clearData()
        val date1 = DateTime().withDate(1997, 2, 10)
        val record = CalorieRecord(1, "date1", 85, date1.millis)
        sleepDao.addRecord(record)
        sleepDao.getByDate(
            date1.withMillisOfDay(0),
            date1.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59)
        ).observeForever {
            Assert.assertEquals(record, it.get(0))
        }
    }

    @Test
    fun getBetweenDates() {
        val date1 = DateTime().withDate(1997, 2, 10)
        val date2 = DateTime().withDate(1997, 2, 11)
        val date3 = DateTime().withDate(1997, 2, 12)
        sleepDao.addRecord(CalorieRecord(1, "date1", 85, date1.millis))
        sleepDao.addRecord(CalorieRecord(2, "date2", 85, date2.millis))
        sleepDao.addRecord(CalorieRecord(3, "date3", 85, date3.millis))
        sleepDao.addRecord(CalorieRecord(4, "date3", 85, date3.millis))
        sleepDao.addRecord(CalorieRecord(5, "date3", 85, date3.millis))
        val records = sleepDao.getBetweenDates(date1, date2)
        Assert.assertEquals(2, records.size)
    }

    @Test
    fun clearDb() {
        sleepDao.clearData()
        val records = sleepDao.getAll()
        Assert.assertEquals(0, records.value?.size)
    }
}