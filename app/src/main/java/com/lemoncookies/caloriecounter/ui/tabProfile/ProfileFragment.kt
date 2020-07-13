package com.lemoncookies.caloriecounter.ui.tabProfile

import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.ui.base.BaseFragment

class ProfileFragment : BaseFragment() {
    override val LAYOUT = R.layout.fragment_profile

    companion object {
        fun newInstance() = ProfileFragment()
    }
}