package com.mishok.core_ar_api.domain

import androidx.lifecycle.LiveData
import com.mishok.core_db_api.models.LocalBuildingPath

interface DoorPathRepository {

    fun getDoorPathByBuildingId(buildingId: Long)

    val pathCoordinates: LiveData<List<LocalBuildingPath>>
}