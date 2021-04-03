package com.mishok.polygo.ui.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.base.FragmentConfiguration
import com.mishok.polygo.base.route.RouteDestination
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.employee_card.EmployeeBottomSheetDialogFragment
import com.mishok.polygo.ui.search.adapter.SearchAdapter
import com.mishok.polygo.utils.AutoClearedValue
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment<SearchState, SearchViewModel>() {

    @Inject
    override lateinit var viewModel: SearchViewModel

    override val layoutRes: Int = R.layout.fragment_search

    private var searchAdapter: SearchAdapter by AutoClearedValue()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initList()
        viewModel.loadSearching()
    }

    private fun initList() = with(itemsRecyclerView) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        searchAdapter = SearchAdapter(viewModel)
        adapter = searchAdapter
        setHasFixedSize(true)
    }

    private fun initViews() {
        searchAllButton.setOnClickListener { viewModel.populateDataBase() }
    }

    override fun onStateChange(state: SearchState) {
        if (state.list.isNotEmpty()) {
            searchAdapter.items = state.list
        }
        if (state.employee != null) {
            openEmployeeCard(state.employee)
        }
    }

    private fun openEmployeeCard(employee: CreateAdapterListItem.EmployeeItem) {
        //EmployeeBottomSheetDialogFragment.newInstance().show(childFragmentManager,"employee")
    }

}