package com.lemoncookies.caloriecounter.ui.tabSettings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.lemoncookies.caloriecounter.R

class ProfileFragment : PreferenceFragmentCompat() {
    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs, rootKey)
    }

    override fun onDisplayPreferenceDialog(preference: Preference) {
        if (preference is EdittextCheckboxPreference) {
            val dialogFragment: EdittextCheckboxDialog? =
                EdittextCheckboxDialog.newInstance(preference.getKey(), preference)
            dialogFragment?.setTargetFragment(this, 0)
            dialogFragment?.show(parentFragmentManager, null)
        } else super.onDisplayPreferenceDialog(preference)
    }
}