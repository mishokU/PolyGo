package com.mishok.polygo.ui.building_inside

import androidx.lifecycle.LifecycleObserver
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.domain.building_inside.BuildingInsideInteractor
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.building_inside.adapter.ChipCallback
import com.mishok.polygo.ui.search.adapter.SearchCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BuildingInsideViewModel @Inject constructor(
    private val buildingInsideInteractor: BuildingInsideInteractor,
    private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<BuildingInsideState>(), LifecycleObserver, SearchCallback, ChipCallback {

    override val initialState: BuildingInsideState = BuildingInsideState()

    fun loadBuildingInfo(buildingId: Long) {
        coroutineScope.launch {
            buildingInsideInteractor.loadBuildingInfoByBuildingId(buildingId).collect {
                switchDispatcher(it)
            }
        }
        populateChips()
        populateInfo()
    }

    private suspend fun switchDispatcher(it: List<CreateAdapterListItem>) {
        withContext(Dispatchers.Main) {
            state = state.copy(list = it)
        }
    }

    override fun onBuildingClick(building: CreateAdapterListItem.BuildingItem) {
        state = state.copy(building = building)
    }

    override fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem) {
        state = state.copy(employee = employee)
    }

    override fun onChipClick(building: CreateAdapterListItem.ChipItem) {

    }

    private fun populateChips() {
        val chips: MutableList<CreateAdapterListItem.ChipItem> = mutableListOf()
        for (i in 0..10) {
            chips.add(CreateAdapterListItem.ChipItem("ЕДА"))
        }
        state = state.copy(chips = chips)
    }

    private fun populateInfo() {
        val list: MutableList<CreateAdapterListItem> = mutableListOf()
        for (i in 0..10) {
            list.add(
                CreateAdapterListItem.BuildingInfoItem(
                    id = 0,
                    title = "ejfiejfwi"
                )
            )
        }
    }

}