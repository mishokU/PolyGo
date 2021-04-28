package com.mishok.polygo.ui.search

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
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.buildingFilterButton
import kotlinx.android.synthetic.main.fragment_search.employeeFilterButton
import kotlinx.android.synthetic.main.fragment_search.itemsRecyclerView
import kotlinx.android.synthetic.main.fragment_search.searchAllButton
import javax.inject.Inject

class SearchFragment : BaseFragment<SearchState, SearchViewModel>() {

    @Inject
    override lateinit var viewModel: SearchViewModel

    override val layoutRes: Int = R.layout.fragment_search

    private var searchAdapter: SearchAdapter by AutoClearedValue()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initSearch()
        initList()
        loadData()
    }

    private fun loadData() {
        viewModel.loadSearching(SearchFilter.ALL)
    }

    private fun initList() = with(itemsRecyclerView) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        searchAdapter = SearchAdapter(object : SearchCallbackWrapper() {
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
        adapter = searchAdapter
        setHasFixedSize(true)
    }

    private fun initViews() {
        searchAllButton.setOnClickListener {
            viewModel.loadSearching(SearchFilter.ALL)
        }
        buildingFilterButton.setOnClickListener {
            viewModel.loadSearching(SearchFilter.BUILDINGS)
        }
        employeeFilterButton.setOnClickListener {
            viewModel.loadSearching(SearchFilter.EMPLOYEE)
        }
        crossMarkIcon.setOnClickListener {
            viewModel.hideCrossMark()
            viewModel.loadSearching(SearchFilter.ALL)
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

    override fun onStateChange(state: SearchState) {
        state.apply {
            searchAdapter.items = list
            if (this.employee != null) {
                openEmployeeCard(this.employee)
                viewModel.resetEmployee()
            }
            if (this.crossMark) {
                crossMarkIcon.visibility = View.VISIBLE
            } else {
                crossMarkIcon.visibility = View.GONE
            }
            if (this.clearText) {
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