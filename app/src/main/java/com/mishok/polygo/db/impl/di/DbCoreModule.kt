package com.mishok.polygo.db.impl.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mishok.core_api.di.IsDebug
import com.mishok.polygo.db.PolyGoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbCoreModule {

    @Provides
    fun provideDatabase(context: Context, @IsDebug isDebug: Boolean): PolyGoDatabase =
        Room.databaseBuilder(context, PolyGoDatabase::class.java, PolyGoDatabase.DATABASE_NAME)
            .apply { if (isDebug) fallbackToDestructiveMigration() }
            .build()

    @Provides
    fun provideEmployeeDao(db: PolyGoDatabase) = db.employeeDao()

    @Provides
    fun provideSearchDao(db: PolyGoDatabase) = db.searchDao()

    @Provides
    fun provideBuildingsDao(db: PolyGoDatabase) = db.buildingsDao()

    @Provides
    fun provideBuildingInfoDao(db: PolyGoDatabase) = db.buildingInfoDao()

}