package com.mishok.polygo.ui.search

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.base.route.RouteDestination
import com.mishok.polygo.db.api.models.LocalBuildings
import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.db.api.models.LocalSearching
import com.mishok.polygo.domain.search.SearchInteractor
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.search.adapter.SearchCallback
import com.mishok.polygo.utils.CoroutineScopeIO
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchViewModel @Inject constructor(
        private val searchInteractor: SearchInteractor,
        private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<SearchState>(), LifecycleObserver, SearchCallback {

    override val initialState: SearchState = SearchState()

    fun loadSearching() {
        coroutineScope.launch {
            searchInteractor.loadAllSearching().collect {
                Log.d("lists", it.toString())
                withContext(Dispatchers.Main) {
                    Log.d("lists", it.toString())
                    state = state.copy(list = it)
                }
            }
        }
    }

    override fun onSearchClick(search: CreateAdapterListItem.SearchItem) {

    }

    override fun onBuildingClick(building: CreateAdapterListItem.BuildingItem) {
        state = state.copy(building = building)
    }

    override fun onEmployeeClick(employee: CreateAdapterListItem.EmployeeItem) {
        navigateTo(RouteDestination.EmployeeDetailed)
        //state = state.copy(employee = employee)
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
                    scheduleUrl = "fjeifefwj"
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
                    title = "iewjfweifjiwe$i"
                )
            )
        }


        coroutineScope.launch {
            searchInteractor.addSearching(list)
            searchInteractor.addBuildings(buildings)
        }
    }

}