package com.lemoncookies.caloriecounter.ui.newItem

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lemoncookies.caloriecounter.databinding.ActivityAddRecordBinding

class AddRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRecordBinding
    private val viewModel: AddRecordViewModel by viewModels()

    companion object {
        val KEY_SELECTED_DATE = "SEL_DATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView() {
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val value = binding.etValue.text.toString().toInt()
            val date: Long = intent.getLongExtra(KEY_SELECTED_DATE, 0L)
            viewModel.saveRecord(name, value, date)
            finish()
        }
    }
}