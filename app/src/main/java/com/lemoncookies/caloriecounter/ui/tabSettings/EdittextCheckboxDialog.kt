package com.lemoncookies.caloriecounter.ui.tabSettings

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceDialogFragmentCompat
import com.lemoncookies.caloriecounter.ui.tabSettings.EdittextCheckboxPreference.Companion.DEF_IS_MIN
import com.lemoncookies.caloriecounter.ui.tabSettings.EdittextCheckboxPreference.Companion.DEF_VALUE
import com.lemoncookies.caloriecounter.views.DialogLimit


class EdittextCheckboxDialog(private val preference: EdittextCheckboxPreference? = null) :
    PreferenceDialogFragmentCompat() {
    private var dialogLimit: DialogLimit? = null

    companion object {
        fun newInstance(
            key: String?,
            preference: EdittextCheckboxPreference
        ): EdittextCheckboxDialog? {
            val fragment = EdittextCheckboxDialog(preference)
            val bundle = Bundle(1)
            bundle.putString(ARG_KEY, key)
            fragment.arguments = bundle
            return fragment
        }
    }

    init {
        val b = Bundle()
        b.putString(ARG_KEY, preference?.key)
        arguments = b
    }

    override fun onCreateDialogView(context: Context?): View {
        val dialogView = getContext()?.let { DialogLimit(it) }
        dialogView?.setValue(preference?.getLimit() ?: DEF_VALUE)
        dialogView?.setCheckbox(preference?.getIsMin() ?: DEF_IS_MIN)
        dialogLimit = dialogView
        return dialogLimit!!
    }

    override fun onStart() {
        super.onStart()
        val dialog =
            dialog as AlertDialog?
        if (preference?.defaultLimit != null && dialogLimit != null && dialog != null
        ) {
            dialog.getButton(DialogInterface.BUTTON_NEUTRAL)
                ?.setOnClickListener {
                    preference.defaultLimit?.let {
                        dialogLimit?.setValue(it)
                    }
                }
        }
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        var value = DEF_VALUE
        val isMin = dialogLimit?.getCheckboxState() ?: DEF_IS_MIN
        if (positiveResult) {
            try {
                value = Integer.parseInt(dialogLimit?.getValue().toString())
            } catch (e: NumberFormatException) {
            }
            if (preference?.callChangeListener(value) == true) {
                preference.setValue(value, isMin)
            }
        }
    }
}