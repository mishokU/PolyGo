package com.mishok.polygo.ui.radar

import android.Manifest
import android.app.Application
import android.content.Context
import com.markodevcic.peko.Peko
import com.markodevcic.peko.PermissionResult
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.base.route.RouteDestination
import com.mishok.polygo.base.route.RouteSection
import com.mishok.polygo.domain.map.MapInteractor
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class RadarViewModel @Inject constructor(
    private val expensive: MapInteractor,
    private val coroutineScope: CoroutineScope,
) : BaseViewModelImpl<RadarState>() {

    override val initialState: RadarState = RadarState()

    fun openAR() {
        navigateTo(RouteDestination.ArFragment)
        state = state.copy(isCamera = false)
    }

    fun requestPermission(context: Context) {
        coroutineScope.launch {
            val result = Peko.requestPermissionsAsync(context, Manifest.permission.CAMERA)
            withContext(Dispatchers.Main) {
                state = if (result is PermissionResult.Granted) {
                    state.copy(isCamera = true)
                } else {
                    state.copy(isCamera = false)
                }
            }
        }
    }

}