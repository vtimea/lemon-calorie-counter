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
import com.lemoncookies.caloriecounter.data.prefs.PrefParams.DEF_CALORIE_LIMIT
import com.lemoncookies.caloriecounter.data.prefs.PrefParams.DEF_IS_MIN
import com.lemoncookies.caloriecounter.data.prefs.PrefsHelper
import com.lemoncookies.caloriecounter.data.prefs.PrefsHelperImpl


class EdittextCheckboxPreference : DialogPreference {
    private val mPrefsHelper: PrefsHelper = PrefsHelperImpl(context)
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
        return if (shouldPersist()) mPrefsHelper.getCalorieLimit() else DEF_CALORIE_LIMIT
    }

    private fun getPersistedIsMin(): Boolean? {
        return if (shouldPersist()) mPrefsHelper.getIsMinimum() else DEF_IS_MIN
    }

    private fun persistLimit(limit: Int) {
        mPrefsHelper.saveCalorieLimit(limit)
    }

    private fun persistIsMin(isMin: Boolean) {
        mPrefsHelper.saveIsMinimum(isMin)
    }

    private fun showLimit(value: Int?) {
        if (mValueView != null) {
            mValueView?.findViewById<TextView>(R.id.value)?.text = value.toString()
        }
    }

    fun setValues(value: Int = DEF_CALORIE_LIMIT, isMin: Boolean = DEF_IS_MIN) {
        persistLimit(value)
        persistIsMin(isMin)
        showLimit(value)
    }

    fun getLimit(): Int {
        return getPersistedLimit() ?: DEF_CALORIE_LIMIT
    }

    fun getIsMin(): Boolean {
        return getPersistedIsMin() ?: DEF_IS_MIN
    }
}