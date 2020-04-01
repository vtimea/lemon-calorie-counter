package com.lemoncookies.caloriecounter.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lemoncookies.caloriecounter.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
