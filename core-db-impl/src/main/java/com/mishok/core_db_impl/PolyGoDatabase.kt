package com.mishok.core_db_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mishok.core_db_api.models.*
import com.mishok.core_db_impl.dao.*

@Database(
    entities = [
        LocalEmployees::class,
        LocalSearching::class,
        LocalBuildings::class,
        LocalBuildingInfo::class,
        LocalBuildingFilters::class
    ], version = 1, exportSchema = false
)
abstract class PolyGoDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    abstract fun searchDao(): SearchDao

    abstract fun buildingsDao(): BuildingDao

    abstract fun buildingInfoDao(): BuildingInfoDao

    abstract fun buildingFiltersDao(): BuildingFiltersDao

    companion object {
        const val DATABASE_NAME = "poly_go_db"
    }
}