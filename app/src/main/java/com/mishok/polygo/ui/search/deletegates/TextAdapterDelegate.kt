package com.mishok.polygo.ui.search.deletegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.mishok.polygo.R
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_title.*

class TextAdapterDelegate() :
    AbsListItemAdapterDelegate<CreateAdapterListItem.BuildingTitleItem, Any, TextAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean =
        item is CreateAdapterListItem.BuildingTitleItem

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
        )
    }

    override fun onBindViewHolder(
        item: CreateAdapterListItem.BuildingTitleItem,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(item: CreateAdapterListItem.BuildingTitleItem) {
            textTitle.text = item.title
        }

    }
}