package com.mishok.polygo.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mishok.polygo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PolyGo)
        setContentView(R.layout.activity_main)
    }

}