package com.mishok.polygo.ui.bookmarks

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.ui.search.adapter.SearchAdapter
import com.mishok.polygo.utils.AutoClearedValue
import com.mishok.polygo.utils.filter.SearchFilter
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


class BookmarksFragment : BaseFragment<BookmarksState, BookmarksViewModel>() {

    @Inject
    override lateinit var viewModel: BookmarksViewModel

    override val layoutRes: Int = R.layout.fragment_bookmarks

    private var bookmarkAdapter: SearchAdapter by AutoClearedValue()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initList()
        loadData()
    }

    private fun loadData() {
        viewModel.loadBookmarks(SearchFilter.ALL)
    }

    private fun initList() = with(itemsRecyclerView) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        bookmarkAdapter = SearchAdapter(viewModel)
        adapter = bookmarkAdapter
        setHasFixedSize(true)
    }

    private fun initViews() {
        searchAllButton.setOnClickListener {
            viewModel.loadBookmarks(SearchFilter.ALL)
        }
        buildingFilterButton.setOnClickListener {
            viewModel.loadBookmarks(SearchFilter.BUILDINGS)
        }
        employeeFilterButton.setOnClickListener {
            viewModel.loadBookmarks(SearchFilter.EMPLOYEE)
        }
    }

    override fun onStateChange(state: BookmarksState) {
        if (state.list.isNotEmpty()) {
            bookmarkAdapter.items = state.list
        }
    }
}