package com.example.voice_recorder.domain.use_cases.deletion

import com.example.voice_recorder.common.Resource
import com.example.voice_recorder.domain.repository.RecordsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteAllSavedItemsUseCase @Inject constructor(
    private val repository: RecordsRepository
) {
    operator fun invoke() = flow {
        try {
            repository.deleteAllSavedRecords()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Can't delete all items"))
        }
    }
}