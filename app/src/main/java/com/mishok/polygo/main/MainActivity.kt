package com.mishok.polygo.main

import android.os.Bundle
import com.mishok.polygo.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PolyGo)
        setContentView(R.layout.activity_main)
    }

}