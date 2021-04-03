package com.mishok.polygo.ui.bookmarks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.base.FragmentConfiguration
import com.mishok.polygo.ui.search.SearchState
import com.mishok.polygo.ui.search.adapter.SearchAdapter
import com.mishok.polygo.utils.AutoClearedValue
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
        viewModel.loadBookmarks()
    }

    private fun initList() = with(itemsRecyclerView) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        bookmarkAdapter = SearchAdapter(viewModel)
        adapter = bookmarkAdapter
        setHasFixedSize(true)
    }

    private fun initViews() {
        //searchAllButton.setOnClickListener { }
    }

    override fun onStateChange(state: BookmarksState) {
        if (state.list.isNotEmpty()) {
            bookmarkAdapter.items = state.list
        }
    }
}