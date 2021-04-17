package com.mishok.polygo.ui.building_inside

import androidx.lifecycle.LifecycleObserver
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.domain.bookmarks.BookmarksInteractor
import com.mishok.polygo.domain.building_inside.BuildingInsideInteractor
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.mishok.polygo.ui.building_inside.adapter.ChipCallback
import com.mishok.polygo.ui.search.adapter.OnBuildingInfoItemClickCallback
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
    LifecycleObserver,
    OnBuildingInfoItemClickCallback,
    ChipCallback {

    override val initialState: BuildingInsideState = BuildingInsideState()

    private var buildingId: Long = 0

    fun loadBuildingInfo(buildingId: Long) {
        this.buildingId = buildingId
        coroutineScope.launch {
            buildingInsideInteractor.loadBuildingInfoByBuildingId(buildingId).collect {
                switchDispatcher(it.groupBy {
                    it.category
                })
            }
        }
        populateChips()
    }

    private suspend fun switchDispatcher(it: Map<String, List<CreateAdapterListItem.BuildingInfoItem>>) {
        withContext(Dispatchers.Main) {
            state = state.copy(map = it)
        }
    }

    fun onBuildingInfoBookmarkClick(item: CreateAdapterListItem.BuildingInfoItem) {
        coroutineScope.launch {
            if (item.inBookmark) {
                bookmarkInteractor.addBuildingInfoBookmark(item.id)
            } else {
                bookmarkInteractor.removeBuildingInfoBookmark(item.id)
            }
        }.invokeOnCompletion {
            loadBuildingInfo(buildingId = buildingId)
        }
    }

    override fun onBuildingInfoItemClick(item: CreateAdapterListItem.BuildingInfoItem) {

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
}

fun String.toTitleModel(): CreateAdapterListItem.BuildingTitleItem {
    return CreateAdapterListItem.BuildingTitleItem(
        title = this
    )
}
