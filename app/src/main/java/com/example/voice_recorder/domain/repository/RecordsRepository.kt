package com.example.voice_recorder.domain.repository

import com.example.voice_recorder.data.local.items.RecordItem
import kotlinx.coroutines.flow.Flow

interface RecordsRepository {
    suspend fun getAllRecords(): Flow<List<RecordItem>>
    suspend fun saveNewRecord(item: RecordItem)
    suspend fun deleteSavedRecordById(id: Int)
    suspend fun deleteAllSavedRecords()
}