package com.mishok.polygo.ui.start

import com.mishok.core_api.utils.LocaleManager
import com.mishok.polygo.base.BaseViewModelImpl
import com.mishok.polygo.base.route.RouteDestination
import javax.inject.Inject

class StartViewModel : BaseViewModelImpl<StartState>() {

    @Inject
    lateinit var localeManager: LocaleManager

    override val initialState: StartState
        get() = StartState()

    fun finishApplication() {
        state = state.copy(finish = true)
    }

    fun openSearchFragment() {
        navigateTo(RouteDestination.Search)
    }

    fun openBookmarkFragment() {
        navigateTo(RouteDestination.Bookmark)
    }

    fun openRadarFragment() {
        navigateTo(RouteDestination.Radar)
    }

    fun openMapFragment() {
        navigateTo(RouteDestination.Map)
    }


}