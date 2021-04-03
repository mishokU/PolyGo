package com.mishok.polygo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mishok.polygo.base.route.RouteDestination
import com.mishok.polygo.db.api.models.LocalBuildings
import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.db.api.models.LocalSearching
import com.mishok.polygo.db.impl.dao.BuildingDao
import com.mishok.polygo.db.impl.dao.EmployeeDao
import com.mishok.polygo.db.impl.dao.SearchDao

@Database(
    entities = [
        LocalEmployees::class,
        LocalSearching::class,
        LocalBuildings::class
    ], version = 1, exportSchema = false
)
abstract class PolyGoDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    abstract fun searchDao(): SearchDao

    abstract fun buildingsDao(): BuildingDao

    companion object {
        const val DATABASE_NAME = "poly_go_db"
    }
}