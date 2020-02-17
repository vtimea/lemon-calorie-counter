package com.lemoncookies.caloriecounter.data.local

import android.app.Application
import com.lemoncookies.caloriecounter.data.local.db.CalorieRepository
import com.lemoncookies.caloriecounter.data.local.db.WeightRepository

class LocalRepository(application: Application) {
    val calorieRepository: CalorieRepository = CalorieRepository(application)
    val weightRepository: WeightRepository = WeightRepository(application)
}