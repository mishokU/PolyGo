package com.mishok.polygo.db.impl.di

import android.content.Context
import androidx.room.Room
import com.mishok.polygo.db.PolyGoDatabase
import dagger.Module
import dagger.Provides

@Module
class DbCoreModule {

    @Provides
    fun provideDatabase(context: Context, isDebug: Boolean): PolyGoDatabase =
        Room.databaseBuilder(context, PolyGoDatabase::class.java, PolyGoDatabase.DATABASE_NAME)
            .apply { if (isDebug) fallbackToDestructiveMigration() }
            .build()

    @Provides
    fun provideEmployeeDao(db: PolyGoDatabase) = db.employeeDao()


}