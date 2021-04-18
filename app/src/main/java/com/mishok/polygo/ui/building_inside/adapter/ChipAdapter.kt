package com.mishok.polygo.ui.building_inside.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.mishok.polygo.ui.base.CreateAdapterListItem

class ChipAdapter(
    callback: ChipCallback
) : AsyncListDifferDelegationAdapter<Any>(ChipItemCallback()) {

    init {
        sequenceOf(
            ChipDelegate(callback),
            ResetFiltersDelegate(callback)
        ).forEach {
            delegatesManager.addDelegate(it)
        }
    }

    class ChipItemCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            if (oldItem.javaClass != newItem.javaClass) return false
            return when (oldItem) {
                is CreateAdapterListItem.ChipItem -> true
                is CreateAdapterListItem.ResetFilter -> true
                else -> error("Incompatible item received: ${oldItem.javaClass}")
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }
}