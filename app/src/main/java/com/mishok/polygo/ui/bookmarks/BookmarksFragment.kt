package com.mishok.polygo.ui.bookmarks

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.core_components.employee_card.EmployeeBottomSheetDialogFragment
import com.mishok.polygo.ui.search.adapter.SearchAdapter
import com.mishok.polygo.ui.search.adapter.SearchCallbackWrapper
import com.mishok.polygo.utils.AutoClearedValue
import com.mishok.polygo.utils.TextWatcherAdapter
import com.mishok.polygo.utils.filter.SearchFilter
import kotlinx.android.synthetic.main.fragment_bookmarks.crossMarkIcon
import kotlinx.android.synthetic.main.fragment_bookmarks.searchField
import kotlinx.android.synthetic.main.fragment_search.buildingFilterButton
import kotlinx.android.synthetic.main.fragment_search.employeeFilterButton
import kotlinx.android.synthetic.main.fragment_search.itemsRecyclerView
import kotlinx.android.synthetic.main.fragment_search.searchAllButton
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
        initSearch()
        loadData()
    }

    private fun loadData() {
        viewModel.loadBookmarks(SearchFilter.ALL)
    }

    private fun initList() = with(itemsRecyclerView) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        bookmarkAdapter = SearchAdapter(object : SearchCallbackWrapper() {
            override fun onBuildingBookmarkClick(item: CreateAdapterListItem.BuildingItem) {
                viewModel.onBuildingBookmarkClick(item)
            }

            override fun onEmployeeBookmarkClick(item: CreateAdapterListItem.EmployeeItem) {
                viewModel.onEmployeeBookmarkClick(item)
            }

            override fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem) {
                viewModel.onEmployeeClick(employee)
            }
        })
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
        crossMarkIcon.setOnClickListener {
            viewModel.hideCrossMark()
            viewModel.loadBookmarks(SearchFilter.ALL)
        }
    }

    private fun initSearch() {
        searchField.addTextChangedListener(object : TextWatcherAdapter() {
            override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
                if (query.toString().isNotEmpty()) {
                    viewModel.search(query.toString())
                } else {
                    viewModel.showCrossMark()
                }
            }
        })
    }

    override fun onStateChange(state: BookmarksState) {
        state.apply {
            bookmarkAdapter.items = list
            if (employee != null) {
                openEmployeeCard(employee)
                viewModel.resetEmployee()
            }
            if (crossMark) {
                crossMarkIcon.visibility = View.VISIBLE
            } else {
                crossMarkIcon.visibility = View.GONE
            }
            if (clearText) {
                searchField.setText("")
            }
        }
    }

    private fun openEmployeeCard(employee: CreateAdapterListItem.EmployeeItem) {
        EmployeeBottomSheetDialogFragment.newInstance(
            EmployeeBottomSheetDialogFragment.NavigationData(
                name = employee.title,
                schedule = employee.schedule,
                position = employee.description,
                avatar = "",
                email = employee.email
            )
        ).show(childFragmentManager, "employee")
    }
}