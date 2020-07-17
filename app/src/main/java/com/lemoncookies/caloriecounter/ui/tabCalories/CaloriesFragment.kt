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
import org.joda.time.DateTime

class CaloriesFragment : BaseFragment() {
    override val LAYOUT = R.layout.fragment_calories
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
        val dates = listOf<DateTime>(
            DateTime.now().minusDays(5),
            DateTime.now().minusDays(4),
            DateTime.now().minusDays(3),
            DateTime.now().minusDays(2),
            DateTime.now().minusDays(1),
            DateTime.now()
        )
        rvDates.adapter = object : DatesAdapter(dates, 1) {
            override fun onDateSelected(date: DateTime) {
                Toast.makeText(requireContext(), date.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}