package com.mishok.polygo.base.route

import androidx.annotation.IdRes
import com.mishok.polygo.R

sealed class RouteSection(@IdRes val graph: Int) {
    object Main : RouteSection(R.id.mainNavGraph)
}

sealed class RouteDestination(@IdRes val destination: Int) {

    object Back : RouteDestination(-1)

    object Search : RouteDestination(R.id.searchFragment)

    object Map : RouteDestination(R.id.mapsFragment)

    object Bookmark : RouteDestination(R.id.bookmarksFragment)

    object BuildingInside : RouteDestination(R.id.buildingInsideFragment)

    object ArFragment : RouteDestination(R.id.baseArFragment)

    object Radar : RouteDestination(R.id.radarFragment)

    object EmployeeDetailed : RouteDestination(R.id.employeeBottomSheetDialogFragment)

    sealed class Login(@IdRes destination: Int) : RouteDestination(destination) {

        //object Credentials : Login(R.id.credentialsFragment)
        //object TermsConditions : Login(R.id.termsConditionsFragment)
    }

}