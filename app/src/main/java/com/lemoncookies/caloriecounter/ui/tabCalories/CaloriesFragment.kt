package com.lemoncookies.caloriecounter.ui.tabCalories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.databinding.FragmentCaloriesBinding
import com.lemoncookies.caloriecounter.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_calories.view.*
import org.joda.time.DateTime

class CaloriesFragment : BaseFragment() {
    override val LAYOUT = R.layout.fragment_calories
    private val DATES_TO_SHOW = 6
    private val viewModel: CaloriesViewModel by viewModels()
    private lateinit var binding: FragmentCaloriesBinding

    companion object {
        fun newInstance() = CaloriesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCaloriesBinding.inflate(inflater, container, false)
        viewModel.records.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = CalorieListAdapter(it)
        })
        viewModel.calorieSum.observe(viewLifecycleOwner, Observer {
            binding.tvSum.text = getString(R.string.kcal, it)
        })
        viewModel.selectedDate.observe(viewLifecycleOwner, Observer {
            setDateText(it)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLists()
    }

    private fun initLists() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val rvDates = binding.rvDates
        rvDates.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val today = DateTime.now()
        val dates: MutableList<DateTime> = mutableListOf()
        for (i in DATES_TO_SHOW downTo 1) {
            dates.add(today.minusDays(i))
        }
        dates.add(today)
        for (i in 1..DATES_TO_SHOW) {
            dates.add(today.plusDays(i))
        }

        rvDates.adapter = object : DatesAdapter(dates, DATES_TO_SHOW) {
            override fun onDateSelected(date: DateTime) {
                onDatePicked(date)
            }
        }
        rvDates.scrollToPosition(DATES_TO_SHOW)
    }

    private fun setDateText(date: DateTime) {
        binding.toolbar.date.text =
            getString(R.string.date_format, date.monthOfYear().asShortText, date.dayOfMonth)
        binding.toolbar.day.text = date.dayOfWeek().asText
    }

    private fun onDatePicked(date: DateTime) {
        viewModel.onDateSelected(date)
        Toast.makeText(requireContext(), date.toString(), Toast.LENGTH_SHORT).show()
    }
}