package com.mishok.polygo.db.api.di

import com.mishok.polygo.db.api.providers.BuildingsProvider
import com.mishok.polygo.db.api.providers.EmployeesProvider
import com.mishok.polygo.db.api.providers.SearchProvider

interface DbCoreApi {

    fun employeeProvider(): EmployeesProvider

    fun searchProvider(): SearchProvider

    fun buildingProvider(): BuildingsProvider
}