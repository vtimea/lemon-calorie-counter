package com.lemoncookies.caloriecounter.ui.tabCalories

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lemoncookies.caloriecounter.data.local.LocalRepository
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import org.joda.time.DateTime

class CaloriesViewModel @ViewModelInject constructor(application: Application) :
    AndroidViewModel(application) {
    private val db: LocalRepository = LocalRepository(application)
    private var selectedDate: MutableLiveData<DateTime> = MutableLiveData(DateTime.now())

    val records: LiveData<List<CalorieRecord>> =
        Transformations.switchMap(selectedDate) { date ->
            db.calorieRepository.getByDate(date)
        }

    val calorieSum: LiveData<Int> = Transformations.map(records) {
        var sum = 0
        for (rec in it) {
            sum += rec.calories
        }
        sum
    }

}