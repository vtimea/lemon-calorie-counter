package com.lemoncookies.caloriecounter.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.lemoncookies.caloriecounter.data.prefs.PrefParams.DEF_CALORIE_LIMIT
import com.lemoncookies.caloriecounter.data.prefs.PrefParams.DEF_IS_MIN

class PrefsHelperImpl(context: Context) : PrefsHelper {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PrefParams.PREF_CAL, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()
    private val liveSharedPrefs: LiveSharedPreferences = LiveSharedPreferences(prefs)

    override fun saveCalorieLimit(limit: Int) {
        editor.putInt(PrefParams.KEY_LIMIT, limit).apply()
    }

    override fun getCalorieLimit(): Int {
        return prefs.getInt(PrefParams.KEY_LIMIT, DEF_CALORIE_LIMIT)
    }

    override fun getCalorieLimitLivePref(): LivePreference<Int> {
        return liveSharedPrefs.getInt(PrefParams.KEY_LIMIT, DEF_CALORIE_LIMIT)
    }

    override fun saveIsMinimum(isMin: Boolean) {
        editor.putBoolean(PrefParams.KEY_ISMIN, isMin).apply()
    }

    override fun getIsMinimum(): Boolean {
        return prefs.getBoolean(PrefParams.KEY_ISMIN, DEF_IS_MIN)
    }

    override fun getIsMinimumLivePref(): LivePreference<Boolean> {
        return liveSharedPrefs.getBoolean(PrefParams.KEY_ISMIN, DEF_IS_MIN)
    }
}