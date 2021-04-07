package com.mishok.polygo.ui.building_inside

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.employee_card.EmployeeBottomSheetDialogFragment
import com.mishok.polygo.ui.search.adapter.SearchAdapter
import com.mishok.polygo.utils.AutoClearedValue
import com.mishok.polygo.utils.filter.SearchFilter
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class BuildingInsideFragment : BaseFragment<BuildingInsideState, BuildingInsideViewModel>() {

    @Inject
    override lateinit var viewModel: BuildingInsideViewModel

    override val layoutRes: Int = R.layout.fragment_search

    private var searchAdapter: SearchAdapter by AutoClearedValue()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initList()
        loadData()
    }

    private fun loadData() {
        viewModel.loadSearching(SearchFilter.ALL)
    }

    private fun initList() = with(itemsRecyclerView) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        searchAdapter = SearchAdapter(viewModel)
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
    }

    override fun onStateChange(state: BuildingInsideState) {
        if (state.list.isNotEmpty()) {
            searchAdapter.items = state.list
        }
        if (state.employee != null) {
            openEmployeeCard(state.employee)
        }
    }

    private fun openEmployeeCard(employee: CreateAdapterListItem.EmployeeItem) {
        EmployeeBottomSheetDialogFragment.newInstance(Bundle())
            .show(childFragmentManager, "employee")
    }

}