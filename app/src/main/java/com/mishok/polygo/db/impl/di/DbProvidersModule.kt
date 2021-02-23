package com.mishok.polygo.db.impl.di

import com.mishok.polygo.db.api.providers.EmployeesProvider
import com.mishok.polygo.db.impl.providers.EmployeeProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface DbProvidersModule {

    @Binds
    fun bindEmployeeProvider(impl: EmployeeProviderImpl): EmployeesProvider


}