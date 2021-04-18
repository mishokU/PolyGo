package com.mishok.polygo.ui.building_inside.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.mishok.polygo.R
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.android.extensions.LayoutContainer


class ResetFiltersDelegate(
    private val onClick: ChipCallback
) : AbsListItemAdapterDelegate<CreateAdapterListItem.ResetFilter, Any, ResetFiltersDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean =
        item is CreateAdapterListItem.ResetFilter

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            containerView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_reset, parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(
        item: CreateAdapterListItem.ResetFilter,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ViewHolder(
        override val containerView: View, val onClick: ChipCallback
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: CreateAdapterListItem.ResetFilter) {
            containerView.setOnClickListener {
                onClick.onResetClick()
            }
        }

    }
}