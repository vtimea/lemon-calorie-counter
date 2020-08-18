package com.lemoncookies.caloriecounter.ui.tabCalories

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.data.local.entities.CalorieRecord
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
        viewModel.selectedDate.observe(viewLifecycleOwner, Observer {
            updateSelectedDate(it)
        })
        viewModel.records.observe(viewLifecycleOwner, Observer {
            updateRecords(it)
        })
        viewModel.calorieSum.observe(viewLifecycleOwner, Observer {
            updateCalorieSum(it)
        })
        viewModel.calorieLimit.observe(viewLifecycleOwner, Observer {
            updateCalorieLimit(it)
        })
        viewModel.calorieState.observe(viewLifecycleOwner, Observer {
            updateCalorieState(getString(it.textRes, it.value), it.warning)
        })
        viewModel.isMinimum.observe(viewLifecycleOwner, Observer {})
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

    private fun onDatePicked(date: DateTime) {
        viewModel.onDateSelected(date)
    }

    private fun updateSelectedDate(date: DateTime) {
        binding.toolbar.date.text =
            getString(R.string.date_format, date.monthOfYear().asShortText, date.dayOfMonth)
        binding.toolbar.day.text = date.dayOfWeek().asText
    }

    private fun updateRecords(records: List<CalorieRecord>) {
        binding.recyclerView.adapter = CalorieListAdapter(records)
    }

    private fun updateCalorieSum(sum: Int) {
        binding.tvSum.text = getString(R.string.kcal, sum, viewModel.calorieLimit.value)
        binding.progress.progress = sum
    }

    private fun updateCalorieLimit(limit: Int) {
        val calorieSum = viewModel.calorieSum.value ?: 0
        binding.progress.max = limit
        binding.tvSum.text = getString(R.string.kcal, calorieSum, limit)
    }

    private fun updateCalorieState(text: String, warning: Boolean) {
        binding.tvOver.text = text
        if (warning) {
            binding.tvOver.setWarning()
        } else {
            binding.tvOver.setDefault()
        }
    }

    fun getSelectedDate(): DateTime? = viewModel.selectedDate.value
}

@Suppress("DEPRECATION")
private fun TextView.setWarning() {
    val warningColor: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(R.color.colorPrimary, null)
    } else {
        resources.getColor(R.color.colorPrimary)
    }
    this.setTextColor(warningColor)
    this.setTypeface(null, Typeface.BOLD)
}

@Suppress("DEPRECATION")
private fun TextView.setDefault() {
    val defaultColor: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(R.color.textSecondary, null)
    } else {
        resources.getColor(R.color.textSecondary)
    }
    this.setTextColor(defaultColor)
    this.setTypeface(null, Typeface.NORMAL)
}