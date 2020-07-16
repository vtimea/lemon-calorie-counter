package com.lemoncookies.caloriecounter.ui.newItem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.lemoncookies.caloriecounter.databinding.ActivityAddRecordBinding
import kotlinx.coroutines.launch

class AddRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRecordBinding
    private lateinit var viewModel: AddRecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddRecordViewModel::class.java)
        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    fun initView() {
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val value = binding.etValue.text.toString().toInt()
            lifecycleScope.launch {
                viewModel.saveRecord(name, value)
            }
            finish()
        }
    }
}