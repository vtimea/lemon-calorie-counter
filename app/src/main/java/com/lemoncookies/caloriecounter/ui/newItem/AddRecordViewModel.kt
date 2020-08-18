package com.lemoncookies.caloriecounter.ui.newItem

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import com.lemoncookies.caloriecounter.data.local.LocalRepository
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import org.joda.time.DateTime

class AddRecordViewModel @ViewModelInject constructor(application: Application) :
    AndroidViewModel(application) {
    private val db: LocalRepository = LocalRepository(application)

    fun saveRecord(name: String, value: Int, date: Long) {
        val recordDate = if (date == 0L) {
            DateTime.now().millis
        } else {
            date
        }
        val record = CalorieRecord(name = name, calories = value, date = recordDate)
        db.calorieRepository.addRecord(record)
    }
}