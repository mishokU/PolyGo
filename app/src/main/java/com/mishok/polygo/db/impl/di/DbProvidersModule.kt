package com.mishok.polygo.db.impl.di

import com.mishok.polygo.db.api.providers.BuildingInfoProvider
import com.mishok.polygo.db.api.providers.BuildingsProvider
import com.mishok.polygo.db.api.providers.EmployeesProvider
import com.mishok.polygo.db.api.providers.SearchProvider
import com.mishok.polygo.db.impl.providers.BuildingInfoProviderImpl
import com.mishok.polygo.db.impl.providers.BuildingsProviderImpl
import com.mishok.polygo.db.impl.providers.EmployeeProviderImpl
import com.mishok.polygo.db.impl.providers.SearchProviderImpl
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

}