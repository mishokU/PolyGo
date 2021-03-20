package com.mishok.polygo.base.api

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.mishok.polygo.base.route.RouteDestination
import com.mishok.polygo.base.route.RouteSection
import com.mishok.polygo.utils.SingleEvent

interface BaseViewModel<VS : BaseViewState> {

    val initialState: VS
    val viewState: MutableLiveData<VS>
    val navigationEvent: MutableLiveData<SingleEvent<NavController.() -> Any>>

    var state: VS
    var stateAsync: VS

    fun navigateTo(route: RouteSection, args: Bundle? = null)
    fun navigateTo(route: RouteDestination, args: Bundle? = null, clearStack: Boolean = false)
    fun onBackPressed() {}
}