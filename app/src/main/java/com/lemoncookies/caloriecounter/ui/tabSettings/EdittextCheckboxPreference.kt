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
        val KEY_LIMIT = "prefGoal_limit"
        val KEY_ISMIN = "prefGoal_ismin"
    }

    private var mValueView: View? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onBindViewHolder(viewHolder: PreferenceViewHolder) {
        mValueView = createValueView(viewHolder.itemView)
        showLimit(getPersistedLimit())
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
        return if (shouldPersist() && sharedPreferences.contains(KEY_LIMIT)) sharedPreferences.getInt(
            KEY_LIMIT,
            DEF_VALUE
        ) else DEF_VALUE
    }

    private fun getPersistedIsMin(): Boolean? {
        return if (shouldPersist() && sharedPreferences.contains(KEY_ISMIN)) sharedPreferences.getBoolean(
            KEY_ISMIN,
            DEF_IS_MIN
        ) else DEF_IS_MIN
    }

    private fun persistLimit(limit: Int) {
        sharedPreferences.edit().putInt(KEY_LIMIT, limit).apply()
    }

    private fun persistIsMin(isMin: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_ISMIN, isMin).apply()
    }

    private fun showLimit(value: Int?) {
        if (mValueView != null) {
            mValueView?.findViewById<TextView>(R.id.value)?.text = value.toString()
        }
    }

    fun setValues(value: Int = DEF_VALUE, isMin: Boolean = DEF_IS_MIN) {
        persistLimit(value)
        persistIsMin(isMin)
        showLimit(value)
    }

    fun getLimit(): Int {
        return getPersistedLimit() ?: DEF_VALUE
    }

    fun getIsMin(): Boolean {
        return getPersistedIsMin() ?: DEF_IS_MIN
    }
}