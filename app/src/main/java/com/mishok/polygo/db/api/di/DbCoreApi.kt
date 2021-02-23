package com.mishok.polygo.db.api.di

import com.mishok.polygo.db.api.providers.EmployeesProvider

interface DbCoreApi {

    fun employeeProvider(): EmployeesProvider

}