package com.lemoncookies.caloriecounter.ui.tabCalories

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.data.local.LocalRepository
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
import com.lemoncookies.caloriecounter.data.prefs.PrefsHelper
import com.lemoncookies.caloriecounter.data.prefs.PrefsHelperImpl
import org.joda.time.DateTime
import kotlin.math.absoluteValue

class CaloriesViewModel @ViewModelInject constructor(application: Application) :
    AndroidViewModel(application) {
    private val mDb: LocalRepository = LocalRepository(application)
    private var mSelectedDate: MutableLiveData<DateTime> = MutableLiveData(DateTime.now())
    private val mPrefsHelper: PrefsHelper = PrefsHelperImpl(application.applicationContext)

    val isMinimum: LiveData<Boolean> =
        Transformations.map(mPrefsHelper.getIsMinimumLivePref()) {
            updateCalorieState(
                calorieLimit.value ?: return@map it,
                it,
                calorieSum.value ?: return@map it
            )
            it
        }

    val selectedDate: LiveData<DateTime> = mSelectedDate

    val calorieLimit: LiveData<Int> = Transformations.map(mPrefsHelper.getCalorieLimitLivePref()) {
        updateCalorieState(it, isMinimum.value ?: return@map it, calorieSum.value ?: return@map it)
        it
    }

    val records: LiveData<List<CalorieRecord>> =
        Transformations.switchMap(mSelectedDate) { date ->
            mDb.calorieRepository.getByDate(date)
        }

    val calorieSum: LiveData<Int> = Transformations.map(records) {
        var sum = 0
        for (rec in it) {
            sum += rec.calories
        }
        updateCalorieState(
            calorieLimit.value ?: return@map sum,
            isMinimum.value ?: return@map sum,
            sum
        )
        sum
    }

    private val mCalorieState: MutableLiveData<CaloriesState> = MutableLiveData()
    val calorieState: LiveData<CaloriesState> = mCalorieState

    private fun updateCalorieState(limit: Int, isMin: Boolean, sum: Int) {
        val diff = limit - sum
        if (diff > 0) {
            mCalorieState.postValue(CaloriesState(R.string.kcal_left, diff, isMin))
        } else {
            mCalorieState.postValue(CaloriesState(R.string.kcal_over, diff.absoluteValue, !isMin))
        }
    }

    fun onDateSelected(date: DateTime) {
        mSelectedDate.postValue(date)
    }
}

class CaloriesState(
    val textRes: Int,
    val value: Int,
    val warning: Boolean
)