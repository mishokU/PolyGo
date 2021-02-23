package com.mishok.polygo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mishok.polygo.db.api.models.LocalEmployees
import com.mishok.polygo.db.impl.dao.EmployeeDao

@Database(
    entities = [
        LocalEmployees::class,
    ], version = 1
)
abstract class PolyGoDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object {
        const val DATABASE_NAME = "poly_go_db"
    }
}