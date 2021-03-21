package com.mishok.polygo.ui.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.base.FragmentConfiguration
import com.mishok.polygo.ui.search.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment<SearchState, SearchViewModel>() {

    @Inject
    override lateinit var viewModel: SearchViewModel

    override var configuration: FragmentConfiguration = FragmentConfiguration(
            layoutRes = R.layout.fragment_search,
            recyclerView = itemsRecyclerView,
            orientation = RecyclerView.VERTICAL,
            adapter = SearchAdapter {

            }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadSearching()
    }

    override fun onStateChange(state: SearchState) {

    }

}