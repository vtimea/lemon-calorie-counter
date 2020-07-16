package com.lemoncookies.caloriecounter.ui.tabCalories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lemoncookies.caloriecounter.R
import com.lemoncookies.caloriecounter.databinding.FragmentCaloriesBinding
import com.lemoncookies.caloriecounter.ui.base.BaseFragment

class CaloriesFragment : BaseFragment() {
    override val LAYOUT = R.layout.fragment_calories
    private lateinit var viewModel: CaloriesViewModel
    private lateinit var binding: FragmentCaloriesBinding

    companion object {
        fun newInstance() = CaloriesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { ViewModelProvider(it).get(CaloriesViewModel::class.java) }!!
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
        initList()
    }

    private fun initList() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}