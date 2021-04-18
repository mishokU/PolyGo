package com.mishok.polygo.ui.building_inside

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.domain.mappers.toTitleModel
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.building_inside.adapter.ChipAdapter
import com.mishok.polygo.ui.search.adapter.SearchAdapter
import com.mishok.polygo.ui.search.adapter.SearchCallbackWrapper
import com.mishok.polygo.utils.AutoClearedValue
import kotlinx.android.synthetic.main.fragment_building_inside.*
import kotlinx.android.synthetic.main.fragment_search.itemsRecyclerView
import javax.inject.Inject

class BuildingInsideFragment : BaseFragment<BuildingInsideState, BuildingInsideViewModel>() {

    @Inject
    override lateinit var viewModel: BuildingInsideViewModel

    override val layoutRes: Int = R.layout.fragment_building_inside

    private var buildingId: Long = 0
    private var chipAdapter: ChipAdapter by AutoClearedValue()
    private var searchAdapter: SearchAdapter by AutoClearedValue()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initViews()
        initList()
        initChips()
        loadData()
    }

    private fun getData() {
        buildingId = arguments?.getLong(KEY_BUILDING_ID) ?: 0
    }

    private fun loadData() {
        viewModel.loadBuildingInfo(buildingId)
    }

    private fun initChips() = with(chipRecyclerView) {
        layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false)
        chipAdapter = ChipAdapter(viewModel)
        adapter = chipAdapter
        setHasFixedSize(true)
    }

    private fun initList() = with(itemsRecyclerView) {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        searchAdapter = SearchAdapter(object : SearchCallbackWrapper() {
            override fun onBuildingInfoBookmarkClick(item: CreateAdapterListItem.BuildingInfoItem) {
                viewModel.onBuildingInfoBookmarkItemClick(item)
            }
        })
        adapter = searchAdapter
        setHasFixedSize(true)
    }

    private fun initViews() {

    }

    override fun onStateChange(state: BuildingInsideState) {
        if (state.chips.isNotEmpty()) {
            chipAdapter.items = state.chips.distinctBy {
                (it as CreateAdapterListItem.ChipItem).title
            }
        }
        if (state.map.isNotEmpty()) {
            searchAdapter.items = state.map.map {
                listOf(it.key.toTitleModel()) + it.value
            }.flatten()
        }
    }

    companion object {
        const val KEY_BUILDING_ID = "KEY_BUILDING_ID"
    }

}