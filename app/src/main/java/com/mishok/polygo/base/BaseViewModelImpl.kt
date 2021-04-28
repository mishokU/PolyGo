package com.mishok.polygo.base

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.mishok.core_api.tags.ModuleTags
import com.mishok.core_ar_api.starter.ArFeatureConfiguration
import com.mishok.core_ar_impl.ArModuleFactory
import com.mishok.polygo.base.api.BaseViewModel
import com.mishok.polygo.base.api.BaseViewState
import com.mishok.polygo.base.route.RouteDestination
import com.mishok.polygo.base.route.RouteSection
import com.mishok.polygo.utils.SingleEvent
import com.mishok.polygo.utils.defaultNavOptions

abstract class BaseViewModelImpl<VS : BaseViewState> : ViewModel(), BaseViewModel<VS> {

    override val viewState: MediatorLiveData<VS> = MediatorLiveData()
    override val navigationEvent: MutableLiveData<SingleEvent<NavController.() -> Any>> = MutableLiveData()

    override var state: VS
        get() = viewState.value ?: initialState
        set(value) = viewState.setValue(value)  // Sets the value synchronously

    override var stateAsync: VS
        get() = state
        set(value) = viewState.postValue(value)  // Sets the value asynchronously

    override fun navigateTo(route: RouteSection, args: Bundle?) {
        withNavController { navigate(route.graph, args, defaultNavOptions) }
    }

    override fun navigateTo(route: RouteDestination, args: Bundle?, clearStack: Boolean) {
        when {
            route is RouteDestination.Back -> withNavController { popBackStack() }
            clearStack -> withNavController { popBackStack(route.destination, false) }
            else -> withNavController {
                initModules(route.module)
                navigate(route.destination, args, defaultNavOptions)
            }
        }
    }

    private fun initModules(module: String?) {
        when (module) {
            ModuleTags.AR -> {
                ArModuleFactory.starter()
                    .startArFeature(ArFeatureConfiguration(tag = ModuleTags.AR))
            }
        }
    }

    private fun withNavController(block: NavController.() -> Any) {
        navigationEvent.postValue(SingleEvent(block))
    }
}
