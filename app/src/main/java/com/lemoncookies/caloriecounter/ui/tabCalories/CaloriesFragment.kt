package com.lemoncookies.caloriecounter.ui.tabCalories

import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.ui.base.BaseFragment

class CaloriesFragment : BaseFragment() {
    override val LAYOUT = R.layout.fragment_calories

    companion object {
        fun newInstance() = CaloriesFragment()
    }
}