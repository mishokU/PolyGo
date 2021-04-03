package com.mishok.polygo.ui.map

import android.Manifest
import android.content.Context
import com.markodevcic.peko.Peko
import com.markodevcic.peko.PermissionResult
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.domain.map.MapInteractor
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    private val mapsInteractor: MapInteractor,
    private val coroutineScope: CoroutineScope
) : BaseViewModelImpl<MapsState>() {

    override val initialState: MapsState = MapsState()

    fun checkOnNearCorp(point: Point) {
        coroutineScope.launch {
            mapsInteractor.loadNearCorps(point).collect {
                withContext(Dispatchers.Main) {
                    state = state.copy(items = it)
                }
            }
        }
    }

    fun getCurrentPosition(context: Context) {
        coroutineScope.launch {
            val result =
                Peko.requestPermissionsAsync(context, Manifest.permission.ACCESS_FINE_LOCATION)
            withContext(Dispatchers.Main) {
                state = if (result is PermissionResult.Granted) {
                    state.copy(isFineLocationGranted = true, moveCamera = true)
                } else {
                    state.copy(isFineLocationGranted = false)
                }
            }
        }
    }

    fun disableMoveCamera() {
        state = state.copy(moveCamera = false)
    }

}