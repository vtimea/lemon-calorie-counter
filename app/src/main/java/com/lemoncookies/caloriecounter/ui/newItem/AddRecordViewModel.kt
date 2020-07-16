package com.lemoncookies.caloriecounter.ui.newItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lemoncookies.caloriecounter.data.local.LocalRepository
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.DateTime

class AddRecordViewModel(application: Application) : AndroidViewModel(application) {
    private val db: LocalRepository = LocalRepository(application)

    suspend fun saveRecord(name: String, value: Int) {
        val record = CalorieRecord(name = name, calories = value, date = DateTime.now().millis)
        withContext(Dispatchers.IO) {
            db.calorieRepository.addRecord(record)
        }
    }
}