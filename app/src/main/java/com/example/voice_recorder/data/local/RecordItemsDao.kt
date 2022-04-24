package com.example.voice_recorder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.voice_recorder.data.local.items.RecordItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordItemsDao {
    @Query("select * from voice_records_table")
    suspend fun getAllRecords(): Flow<List<RecordItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecordItem(item: RecordItem)

    @Query("delete from voice_records_table where id = :id")
    suspend fun deleteRecordById(id: Int)

    @Query("delete from voice_records_table")
    suspend fun deleteAllRecords()
}