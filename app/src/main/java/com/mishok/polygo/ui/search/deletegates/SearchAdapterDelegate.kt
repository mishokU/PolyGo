package com.mishok.polygo.ui.search.deletegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.mishok.polygo.R
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.adapter.SearchCallback
import kotlinx.android.extensions.LayoutContainer

class SearchAdapterDelegate(
        private val onClick: (SearchCallback) -> Unit
) : AbsListItemAdapterDelegate<
        CreateAdapterListItem.SearchItem, Any,
        SearchAdapterDelegate.ViewHolder
        >() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean = item is CreateAdapterListItem.SearchItem

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
                containerView = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false),
                onClick = onClick
        )
    }

    override fun onBindViewHolder(item: CreateAdapterListItem.SearchItem, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class ViewHolder(
            override val containerView: View,
            onClick: (SearchCallback) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: CreateAdapterListItem.SearchItem) {

        }

    }
}