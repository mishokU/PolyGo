package com.mishok.polygo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mishok.polygo.ui.bookmarks.BookmarksViewModel
import com.mishok.polygo.ui.map.MapsViewModel
import com.mishok.polygo.ui.radar.RadarViewModel
import com.mishok.polygo.ui.search.SearchViewModel
import com.mishok.polygo.ui.start.StartViewModel

@Suppress("UNCHECKED_CAST")
class SharedViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            StartViewModel::class.java -> StartViewModel() as T
            MapsViewModel::class.java -> MapsViewModel() as T
            BookmarksViewModel::class.java -> BookmarksViewModel() as T
            RadarViewModel::class.java -> RadarViewModel() as T
            else -> throw IllegalAccessException(modelClass.name)
        }
    }
}