package com.lemoncookies.caloriecounter.data.prefs

import android.content.SharedPreferences
import io.reactivex.rxjava3.subjects.PublishSubject

class LiveSharedPreferences constructor(private val preferences: SharedPreferences) {
    private val publisher = PublishSubject.create<String>()
    private val listener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key -> publisher.onNext(key) }

    private val updates = publisher.doOnSubscribe {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }.doOnDispose {
        if (!publisher.hasObservers())
            preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun getPreferences(): SharedPreferences {
        return preferences
    }

    fun getInt(key: String, defaultValue: Int): LivePreference<Int> {
        return LivePreference(updates, preferences, key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean): LivePreference<Boolean> {
        return LivePreference(updates, preferences, key, defaultValue)
    }
}