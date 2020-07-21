package com.lemoncookies.caloriecounter.data.prefs

interface PrefsHelper {
    fun saveCalorieLimit(limit: Int)

    fun getCalorieLimit(): Int

    fun getCalorieLimitLivePref(): LivePreference<Int>

    fun saveIsMinimum(isMin: Boolean)

    fun getIsMinimum(): Boolean

    fun getIsMinimumLivePref(): LivePreference<Boolean>
}