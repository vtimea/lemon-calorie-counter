package com.lemoncookies.caloriecounter.ui.tabGraphs

import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.ui.base.BaseFragment

class GraphsFragment : BaseFragment() {
    override val LAYOUT = R.layout.fragment_graphs

    companion object {
        fun newInstance() = GraphsFragment()
    }
}