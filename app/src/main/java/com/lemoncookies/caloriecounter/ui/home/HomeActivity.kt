package com.lemoncookies.caloriecounter.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.databinding.ActivityHomeBinding
import com.lemoncookies.caloriecounter.ui.newItem.AddRecordActivity
import com.lemoncookies.caloriecounter.ui.tabCalories.CaloriesFragment
import com.lemoncookies.caloriecounter.ui.tabGraphs.GraphsFragment
import com.lemoncookies.caloriecounter.ui.tabSettings.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView() {
        binding.pager.isUserInputEnabled = false
        binding.pager.adapter = FadingPagerAdapter(supportFragmentManager, lifecycle)
        binding.pager.setPageTransformer { page, position ->
            page.alpha = 0f
            page.visibility = View.VISIBLE
            page.animate()
                .alpha(1f).duration =
                page.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
        }
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = getDrawable(R.drawable.tab_icon_calories)
                    tab.text = getString(R.string.menu_title_calories)
                }
                1 -> {
                    tab.icon = getDrawable(R.drawable.tab_icon_analytics)
                    tab.text = getString(R.string.menu_title_graphs)
                }
                else -> {
                    tab.icon = getDrawable(R.drawable.tab_icon_profile)
                    tab.text = getString(R.string.menu_title_settings)
                }
            }
        }.attach()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: return
                showAddButton(position == 0)
            }
        })
        binding.fabAdd.setOnClickListener {
            onAddButtonPressed(binding.tabLayout.selectedTabPosition)
        }
    }

    private inner class FadingPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle) {
        val fragments = listOf(
            CaloriesFragment.newInstance(),
            GraphsFragment.newInstance(),
            ProfileFragment.newInstance()
        )

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    private fun showAddButton(visible: Boolean) {
        val visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
        if (visibility == binding.fabAdd.visibility) return
        if (visible) {
            binding.fabAdd.show()
        } else {
            binding.fabAdd.hide()
        }
    }

    private fun onAddButtonPressed(tab: Int) {
        when (tab) {
            0 -> {
                navigateToAdd()
            }
        }
    }

    private fun navigateToAdd() {
        val intent = Intent(this, AddRecordActivity::class.java)
        startActivity(intent)
    }
}
