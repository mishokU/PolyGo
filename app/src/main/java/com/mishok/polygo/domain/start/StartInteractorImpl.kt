package com.mishok.polygo.domain.start

import com.mishok.core_db_api.providers.BuildingFiltersProvider
import com.mishok.core_db_api.providers.BuildingInfoProvider
import com.mishok.core_db_api.providers.BuildingsProvider
import com.mishok.core_db_api.providers.EmployeesProvider
import javax.inject.Inject

class StartInteractorImpl @Inject constructor(
    private val buildingsProvider: BuildingsProvider,
    private val employeesProvider: EmployeesProvider,
    private val buildingInfoProvider: BuildingInfoProvider,
    private val buildingInfoFiltersProvider: BuildingFiltersProvider
) : StartInteractor {

    override fun initLocalDatabase() {

    }

}