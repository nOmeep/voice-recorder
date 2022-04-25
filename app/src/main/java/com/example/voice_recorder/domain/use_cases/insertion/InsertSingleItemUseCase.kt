package com.example.voice_recorder.domain.use_cases.insertion

import com.example.voice_recorder.common.Resource
import com.example.voice_recorder.data.local.items.RecordItem
import com.example.voice_recorder.domain.repository.RecordsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertSingleItemUseCase @Inject constructor(
    private val repository: RecordsRepository
) {
    operator fun invoke(item: RecordItem) = flow {
        try {
            repository.saveNewRecord(item)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Can't save this record"))
        }
    }
}