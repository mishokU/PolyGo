package com.mishok.polygo.ui.building_inside

import androidx.lifecycle.LifecycleObserver
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.domain.bookmarks.BookmarksInteractor
import com.mishok.polygo.domain.building_inside.BuildingInsideInteractor
import com.mishok.polygo.domain.mappers.toChipItem
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.building_inside.adapter.ChipCallback
import com.mishok.polygo.ui.search.adapter.OnBuildingInfoBookmarkItemClickCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BuildingInsideViewModel @Inject constructor(
    private val buildingInsideInteractor: BuildingInsideInteractor,
    private val bookmarkInteractor: BookmarksInteractor,
    private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<BuildingInsideState>(),
    OnBuildingInfoBookmarkItemClickCallback,
    LifecycleObserver,
    ChipCallback {

    override val initialState: BuildingInsideState = BuildingInsideState()

    private var buildingId: Long = 0

    fun loadBuildingInfo(buildingId: Long) {
        this.buildingId = buildingId
        coroutineScope.launch {
            buildingInsideInteractor.loadBuildingInfoByBuildingId(buildingId).collect {
                switchDispatcher(it.groupBy { item ->
                    item.category
                })
                switchChipsDispatcher(it.map { item ->
                    item.toChipItem()
                })
            }
        }
    }

    override fun onBuildingInfoItemClick(item: CreateAdapterListItem.BuildingInfoItem) {

    }

    override fun onResetClick() {
        coroutineScope.launch {
            buildingInsideInteractor.loadBuildingInfoByBuildingId(buildingId).collect {
                switchDispatcher(it.groupBy { item ->
                    item.category
                })
            }
        }
    }

    override fun onChipClick(chip: CreateAdapterListItem.ChipItem) {
        coroutineScope.launch {
            buildingInsideInteractor.filterBuildingInfoByCategory(chip).collect {
                switchDispatcher(it.groupBy { item ->
                    item.category
                })
            }
        }
    }

    private suspend fun switchChipsDispatcher(chips: List<CreateAdapterListItem.ChipItem>) {
        withContext(Dispatchers.Main) {
            state = state.copy(chips = chips)
        }
    }

    private suspend fun switchDispatcher(it: Map<String, List<CreateAdapterListItem.BuildingInfoItem>>) {
        withContext(Dispatchers.Main) {
            state = state.copy(map = it)
        }
    }

    fun onBuildingInfoBookmarkItemClick(item: CreateAdapterListItem.BuildingInfoItem) {
        coroutineScope.launch {
            if (item.inBookmark) {
                bookmarkInteractor.removeBuildingInfoBookmark(item.id)
            } else {
                bookmarkInteractor.addBuildingInfoBookmark(item.id)
            }
        }.invokeOnCompletion {
            loadBuildingInfo(buildingId)
        }
    }
}
