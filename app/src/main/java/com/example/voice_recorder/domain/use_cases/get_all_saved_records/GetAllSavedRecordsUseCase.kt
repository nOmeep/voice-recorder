package com.example.voice_recorder.domain.use_cases.get_all_saved_records

import com.example.voice_recorder.common.Resource
import com.example.voice_recorder.data.local.items.RecordItem
import com.example.voice_recorder.domain.repository.RecordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllSavedRecordsUseCase @Inject constructor(
    private val repository: RecordsRepository
) {
    operator fun invoke(): Flow<Resource<List<RecordItem>>> = flow {
        try {
            emit(Resource.Loading())
            val savedRecordsList = repository.getAllRecords().first()
            emit(Resource.Success(savedRecordsList))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something goes wrong"))
        }
    }
}