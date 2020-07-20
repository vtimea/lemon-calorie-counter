package com.lemoncookies.caloriecounter.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.lemoncookies.caloriecounter.R
import kotlinx.android.synthetic.main.dialog_pref_limit.view.*

class DialogLimit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :
    ConstraintLayout(context, attrs, defStyle) {
    val mView: View
    val mEtValue: EditText
    val mCheckbox: CheckBox

    init {
        mView = View.inflate(context, R.layout.dialog_pref_limit, this)
        mEtValue = mView.findViewById(R.id.etValue)
        mCheckbox = mView.findViewById(R.id.cbIsMin)
    }

    fun setValue(value: Int) {
        etValue.setText(value.toString())
    }

    fun getValue(): Int {
        val value: Int
        try {
            value = Integer.parseInt(etValue.text.toString())
        } catch (e: NumberFormatException) {
            return -1
        }
        return value
    }

    fun setCheckbox(isChecked: Boolean) {
        mCheckbox.isChecked = isChecked
    }

    fun getCheckboxState(): Boolean {
        return mCheckbox.isChecked
    }
}