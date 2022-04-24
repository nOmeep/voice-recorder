package com.example.voice_recorder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.voice_recorder.data.local.items.RecordItem

@Database(entities = [RecordItem::class], version = 1)
abstract class RecordsDatabase : RoomDatabase() {
    abstract fun recordItemsDao(): RecordItemsDao
}