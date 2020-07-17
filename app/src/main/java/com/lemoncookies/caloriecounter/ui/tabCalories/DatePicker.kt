package com.lemoncookies.caloriecounter.ui.tabCalories

import org.joda.time.DateTime

interface DatePicker {
    fun onDateSelected(date: DateTime)
}