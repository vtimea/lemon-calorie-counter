package com.lemoncookies.caloriecounter.data.local

import android.app.Application
import com.lemoncookies.caloriecounter.data.local.db.CalorieRepository

class LocalRepository(application: Application) {
    val calorieRepository: CalorieRepository = CalorieRepository(application)
}