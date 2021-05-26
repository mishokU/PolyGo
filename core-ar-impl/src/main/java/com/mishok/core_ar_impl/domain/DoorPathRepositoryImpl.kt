package com.mishok.core_ar_impl.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mishok.core_ar_api.domain.DoorPathRepository
import com.mishok.core_db_api.models.LocalBuildingPath
import com.mishok.core_db_api.providers.BuildingPathProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DoorPathRepositoryImpl @Inject constructor(
    private val buildingPathProvider: BuildingPathProvider
) : DoorPathRepository {

    private val _pathCoordinates = MutableLiveData<List<LocalBuildingPath>>()
    override val pathCoordinates: LiveData<List<LocalBuildingPath>>
        get() = _pathCoordinates

    override fun getDoorPathByBuildingId(buildingId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            buildingPathProvider.getPathByBuildingId(buildingId).collect {
                _pathCoordinates.postValue(it)
            }
        }
    }
}