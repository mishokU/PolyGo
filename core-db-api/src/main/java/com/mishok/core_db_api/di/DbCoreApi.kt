package com.mishok.core_db_api.di

import com.mishok.core_db_api.providers.*

interface DbCoreApi {

    fun employeeProvider(): EmployeesProvider

    fun searchProvider(): SearchProvider

    fun buildingProvider(): BuildingsProvider

    fun buildingInfoProvider(): BuildingInfoProvider

    fun buildingFilterProvider(): BuildingFiltersProvider
}