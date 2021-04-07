package com.mishok.polygo.ui.building_inside

import androidx.lifecycle.LifecycleObserver
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.db.api.models.LocalBuildings
import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.domain.search.SearchInteractor
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.adapter.SearchCallback
import com.mishok.polygo.utils.filter.SearchFilter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class BuildingInsideViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor,
    private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<BuildingInsideState>(), LifecycleObserver, SearchCallback {

    override val initialState: BuildingInsideState = BuildingInsideState()

    fun loadSearching(filter: SearchFilter) {
        coroutineScope.launch {
            when (filter) {
                SearchFilter.ALL -> {
                    searchInteractor.loadAllSearching().collect {
                        switchDispatcher(it)
                    }
                }
                SearchFilter.EMPLOYEE -> {
                    searchInteractor.loadEmployees().collect {
                        switchDispatcher(it)
                    }
                }
                SearchFilter.BUILDINGS -> {
                    searchInteractor.loadBuildings().collect {
                        switchDispatcher(it)
                    }
                }
            }
        }
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

    fun populateDataBase() {
        val list: MutableList<LocalEmployees> = mutableListOf()
        for (i in 0..10) {
            list.add(
                LocalEmployees(
                    id = 0,
                    name = "feifwifwe",
                    avatar = "fefefwe",
                    position = "efiejwfiwjfwfjwefw",
                    contacts = "ekfwiejwe",
                    scheduleUrl = "fjeifefwj$i",
                    saved = false
                )
            )
        }
        val buildings: MutableList<LocalBuildings> = mutableListOf()
        for (i in 0..10) {
            buildings.add(
                LocalBuildings(
                    audienceId = 0,
                    distance = 0,
                    id = 0,
                    latitude = (0 + i * 20).toLong(),
                    corpId = 0,
                    floorId = 0,
                    longitude = (0 + i * 10).toLong(),
                    time = 10,
                    title = "iewjfweifjiwe$i",
                    saved = false
                )
            )
        }


        coroutineScope.launch {
            searchInteractor.addSearching(list)
            searchInteractor.addBuildings(buildings)
        }
    }

}