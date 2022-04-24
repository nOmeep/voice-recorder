package com.example.voice_recorder.di

import android.app.Application
import androidx.room.Room
import com.example.voice_recorder.common.Constants.DATABASE_NAME
import com.example.voice_recorder.data.local.RecordsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRecordItemsDatabase(app: Application) =
        Room.databaseBuilder(app, RecordsDatabase::class.java, DATABASE_NAME)
            .build()
}