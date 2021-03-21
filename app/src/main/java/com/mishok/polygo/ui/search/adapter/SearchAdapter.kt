package com.mishok.polygo.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.deletegates.SearchAdapterDelegate

class SearchAdapter(callback: (SearchCallback) -> Unit) : AsyncListDifferDelegationAdapter<Any>(SearchItemCallback()) {

    init {
        sequenceOf(
                SearchAdapterDelegate(callback)
        ).forEach {
            delegatesManager.addDelegate(it)
        }
    }

    class SearchItemCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            if (oldItem is CreateAdapterListItem.SearchItem && newItem is CreateAdapterListItem.SearchItem) {
                return oldItem.id == newItem.id
            }
            return false
        }
    }
}