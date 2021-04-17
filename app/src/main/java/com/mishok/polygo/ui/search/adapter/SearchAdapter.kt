package com.mishok.polygo.ui.search.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.deletegates.BuildingInfoAdapterDelegate
import com.mishok.polygo.ui.search.deletegates.*

class SearchAdapter(
    callback: SearchCallbackWrapper
) : AsyncListDifferDelegationAdapter<Any>(SearchItemCallback()) {

    init {
        sequenceOf(
            SearchAdapterDelegate(callback),
            BuildingsAdapterDelegate(callback),
            BuildingInfoAdapterDelegate(callback),
            EmployeeAdapterDelegate(callback),
            TextAdapterDelegate(callback)
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
                is CreateAdapterListItem.BuildingInfoItem -> true
                is CreateAdapterListItem.BuildingTitleItem -> true
                else -> error("Incompatible item received: ${oldItem.javaClass}")
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}