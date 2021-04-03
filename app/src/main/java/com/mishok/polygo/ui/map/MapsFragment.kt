package com.mishok.polygo.ui.map


import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import com.mishok.core_api.utils.PolyGoLocationManager
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import com.mishok.polygo.ui.base.CreateAdapterListItem
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateSource
import com.yandex.runtime.Runtime.getApplicationContext
import kotlinx.android.synthetic.main.fragment_maps.*
import javax.inject.Inject


class MapsFragment : BaseFragment<MapsState, MapsViewModel>() {

    @Inject
    override lateinit var viewModel: MapsViewModel

    @Inject
    lateinit var polyGoLocationManager: PolyGoLocationManager

    override val layoutRes: Int = R.layout.fragment_maps

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveCamera(POLITECH_LATITUDE, POLITECH_LONGITUDE)
        mapview.map.addCameraListener { _, cameraPosition, cameraUpdateSource, _ ->
            if (cameraUpdateSource == CameraUpdateSource.GESTURES) {
                viewModel.checkOnNearCorp(cameraPosition.target)
            }
        }
        locationButton.setOnClickListener {
            viewModel.getCurrentPosition(requireContext())
        }
    }

    override fun onStop() {
        super.onStop()
        mapview.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        super.onStart()
        mapview.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStateChange(state: MapsState) {
        val items = state.items
        if (items.isNotEmpty()) {
            buildingName.text = (items[0] as CreateAdapterListItem.BuildingItem).title
        } else {
            buildingName.text = getString(R.string.no_near_objects)
        }

        if (state.isFineLocationGranted && state.moveCamera) {
            if (polyGoLocationManager.latitude != 0.0 && polyGoLocationManager.longitude != 0.0) {
                moveCamera(polyGoLocationManager.latitude, polyGoLocationManager.longitude)
            }
            viewModel.disableMoveCamera()
        }
    }

    private fun moveCamera(latitude: Double, longitude: Double) {
        mapview.map.move(
            CameraPosition(
                Point(latitude, longitude),
                START_ZOOM, 0.0f, 0.0f
            ),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )
    }

    companion object {
        private const val POLITECH_LONGITUDE = 30.37202657040532
        private const val POLITECH_LATITUDE = 60.007587978274294
        private const val START_ZOOM = 20.0F
    }
}