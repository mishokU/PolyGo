package com.mishok.core_db_impl.di

import android.content.Context
import androidx.room.Room
import com.mishok.core_api.di.IsDebug
import com.mishok.core_db_impl.PolyGoDatabase
import dagger.Module
import dagger.Provides

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

    @Provides
    fun provideBuildingFiltersDao(db: PolyGoDatabase) = db.buildingFiltersDao()

}