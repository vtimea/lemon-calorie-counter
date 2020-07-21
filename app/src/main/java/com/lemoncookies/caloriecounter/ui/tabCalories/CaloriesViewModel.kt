package com.lemoncookies.caloriecounter.ui.tabCalories

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lemoncookies.caloriecounter.data.local.LocalRepository
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import com.lemoncookies.caloriecounter.data.prefs.LivePreference
import com.lemoncookies.caloriecounter.data.prefs.PrefsHelper
import com.lemoncookies.caloriecounter.data.prefs.PrefsHelperImpl
import org.joda.time.DateTime

class CaloriesViewModel @ViewModelInject constructor(application: Application) :
    AndroidViewModel(application) {
    private val mDb: LocalRepository = LocalRepository(application)
    private var mSelectedDate: MutableLiveData<DateTime> = MutableLiveData(DateTime.now())
    private val mPrefsHelper: PrefsHelper = PrefsHelperImpl(application.applicationContext)

    val records: LiveData<List<CalorieRecord>> =
        Transformations.switchMap(mSelectedDate) { date ->
            mDb.calorieRepository.getByDate(date)
        }

    val calorieSum: LiveData<Int> = Transformations.map(records) {
        var sum = 0
        for (rec in it) {
            sum += rec.calories
        }
        sum
    }

    val selectedDate: LiveData<DateTime> = mSelectedDate

    val calorieLimit: LivePreference<Int> = mPrefsHelper.getCalorieLimitLivePref()

    val isMinimum: LivePreference<Boolean> = mPrefsHelper.getIsMinimumLivePref()

    fun onDateSelected(date: DateTime) {
        mSelectedDate.postValue(date)
    }
}