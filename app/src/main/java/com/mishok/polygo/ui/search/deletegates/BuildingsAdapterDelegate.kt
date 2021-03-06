package com.mishok.polygo.ui.search.deletegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.mishok.polygo.R
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.adapter.SearchCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_search.*

class BuildingsAdapterDelegate(
    private val onClick: SearchCallback
) : AbsListItemAdapterDelegate<CreateAdapterListItem.BuildingItem, Any, BuildingsAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean =
        item is CreateAdapterListItem.BuildingItem

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            containerView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(
        item: CreateAdapterListItem.BuildingItem,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            holder.bind(item)
        } else {
            holder.applyPayload(payloads.first() as ItemPayload)
        }
    }

    class ViewHolder(
        override val containerView: View, val onClick: SearchCallback
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var item: CreateAdapterListItem.BuildingItem? = null

        init {
            bookmarkImage.setOnClickListener {
                item?.let(onClick::onBuildingBookmarkClick)
            }
        }

        fun bind(item: CreateAdapterListItem.BuildingItem) {
            this.item = item
            name.text = item.title
            toggleBookmark(item.inBookmark)
        }

        private fun toggleBookmark(inBookmark: Boolean) {
            if (inBookmark) {
                bookmarkImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        containerView.context,
                        R.drawable.ic_mark_button_enabled_left
                    )
                )
            } else {
                bookmarkImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        containerView.context,
                        R.drawable.ic_mark_button_disabled
                    )
                )
            }
        }

        fun applyPayload(itemPayload: ItemPayload) {
            toggleBookmark(itemPayload.inBookmark)
        }
    }

    data class ItemPayload(val inBookmark: Boolean)
}