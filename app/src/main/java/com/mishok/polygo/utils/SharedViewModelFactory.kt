package com.mishok.polygo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishok.polygo.start.StartViewModel

class SharedViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            StartViewModel::class.java -> StartViewModel() as T
            else -> throw IllegalAccessException(modelClass.name)
        }
    }
}