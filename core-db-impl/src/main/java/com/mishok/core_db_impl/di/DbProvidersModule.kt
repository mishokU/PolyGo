package com.mishok.core_db_impl.di

import com.mishok.core_db_api.providers.*
import com.mishok.core_db_impl.providers.*
import dagger.Binds
import dagger.Module

@Module
interface DbProvidersModule {

    @Binds
    fun bindEmployeeProvider(impl: EmployeeProviderImpl): EmployeesProvider

    @Binds
    fun bindSearchProvider(impl: SearchProviderImpl): SearchProvider

    @Binds
    fun bindBuildingsProvider(impl: BuildingsProviderImpl): BuildingsProvider

    @Binds
    fun bindBuildingInfoProvider(impl: BuildingInfoProviderImpl): BuildingInfoProvider

    @Binds
    fun bindBuildingFiltersProvider(impl: BuildingFiltersProviderImpl): BuildingFiltersProvider

    @Binds
    fun bindBuildingPathProvider(impl: BuildingPathProviderImpl): BuildingPathProvider

}