package com.mishok.polygo.base

import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

data class FragmentConfiguration(
        val layoutRes: Int,
        val adapter: AsyncListDifferDelegationAdapter<Any>,
        val recyclerView: RecyclerView,
        val orientation: Int = RecyclerView.VERTICAL
)
