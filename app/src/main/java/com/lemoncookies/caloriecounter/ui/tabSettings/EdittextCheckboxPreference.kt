package com.lemoncookies.caloriecounter.ui.tabSettings

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.preference.DialogPreference
import androidx.preference.PreferenceViewHolder
import com.lemoncookies.caloriecounter.R


class EdittextCheckboxPreference : DialogPreference {
    companion object {
        val DEF_VALUE: Int = 2000
        val DEF_IS_MIN: Boolean = false
        private val KEY_LIMIT = "limit"
        private val KEY_ISMIN = "ismin"
    }

    var defaultLimit: Int? = DEF_VALUE
    var defaultisMin: Boolean? = DEF_IS_MIN
    private var mValueView: View? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onBindViewHolder(viewHolder: PreferenceViewHolder) {
        mValueView = createValueView(viewHolder.itemView)
        showValue(getPersistedLimit())
        super.onBindViewHolder(viewHolder)
    }

    private fun createValueView(view: View): View? {
        val widgetFrameView = view.findViewById(android.R.id.widget_frame) as LinearLayout
        widgetFrameView.visibility = View.VISIBLE
        widgetFrameView.removeAllViews()
        LayoutInflater.from(context).inflate(
            R.layout.pref_calorie_value,
            widgetFrameView
        )
        return widgetFrameView.findViewById(R.id.value)
    }

    private fun getPersistedLimit(): Int? {
        val key = key + KEY_LIMIT
        return if (shouldPersist() && sharedPreferences.contains(key)) sharedPreferences.getInt(
            key,
            DEF_VALUE
        ) else DEF_VALUE
    }

    private fun getPersistedIsMin(): Boolean? {
        val key = key + KEY_ISMIN
        return if (shouldPersist() && sharedPreferences.contains(key)) sharedPreferences.getBoolean(
            key,
            DEF_IS_MIN
        ) else DEF_IS_MIN
    }

    private fun persistLimit(limit: Int) {
        val key = key + KEY_LIMIT
        sharedPreferences.edit().putInt(key, limit).apply()
    }

    private fun persistIsMin(isMin: Boolean) {
        val key = key + KEY_ISMIN
        sharedPreferences.edit().putBoolean(key, isMin).apply()
    }

    private fun showValue(value: Int?) {
        if (mValueView != null) {
            mValueView?.findViewById<TextView>(R.id.value)?.text = value.toString()
        }
    }

    fun setValue(value: Int = DEF_VALUE, isMin: Boolean = DEF_IS_MIN) {
        persistLimit(value)
        persistIsMin(isMin)
        showValue(value)
    }

    fun getLimit(): Int {
        return getPersistedLimit() ?: DEF_VALUE
    }

    fun getIsMin(): Boolean {
        return getPersistedIsMin() ?: DEF_IS_MIN
    }
}