package com.mishok.polygo.ui.building_inside.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.mishok.polygo.R
import com.mishok.polygo.ui.base.CreateAdapterListItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chip.*

class ChipDelegate(
    private val onClick: ChipCallback
) : AbsListItemAdapterDelegate<CreateAdapterListItem.ChipItem, Any, ChipDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean =
        item is CreateAdapterListItem.ChipItem

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            containerView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chip, parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(
        item: CreateAdapterListItem.ChipItem,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ViewHolder(
        override val containerView: View, val onClick: ChipCallback
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: CreateAdapterListItem.ChipItem) {
            chip.text = item.title
            containerView.setOnClickListener {
                onClick.onChipClick(item)
            }
        }

    }
}