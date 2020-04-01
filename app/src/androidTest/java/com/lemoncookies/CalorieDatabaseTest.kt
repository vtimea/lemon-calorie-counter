package com.lemoncookies

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.lemoncookies.caloriecounter.data.local.db.CalorieRepository
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CalorieDatabaseTest {
    private lateinit var database: CalorieRepository

    @Before
    fun initDatabase() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        database = CalorieRepository(application)
    }

    @Test
    fun insertRecord() {
        val id: Long = 1
        val record = CalorieRecord(id, "Alma", 85.0f, System.currentTimeMillis())
        database.addRecord(record)
        val testRecord = database.getById(id)
        Assert.assertEquals(record, testRecord)
    }

}