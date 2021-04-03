package com.mishok.polygo.ui.search.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.deletegates.BuildingsAdapterDelegate
import com.mishok.polygo.ui.search.deletegates.EmployeeAdapterDelegate
import com.mishok.polygo.ui.search.deletegates.SearchAdapterDelegate

class SearchAdapter(callback: SearchCallback) :
    AsyncListDifferDelegationAdapter<Any>(SearchItemCallback()) {

    init {
        sequenceOf(
            SearchAdapterDelegate(callback),
            BuildingsAdapterDelegate(callback),
            EmployeeAdapterDelegate(callback)
        ).forEach {
            delegatesManager.addDelegate(it)
        }
    }

    class SearchItemCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            if (oldItem.javaClass != newItem.javaClass) return false
            return when (oldItem) {
                is CreateAdapterListItem.BuildingItem -> true
                is CreateAdapterListItem.EmployeeItem -> true
                is CreateAdapterListItem.BookmarkItem -> true
                else -> error("Incompatible item received: ${oldItem.javaClass}")
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}