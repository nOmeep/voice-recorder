package com.example.voice_recorder.domain.use_cases.deletion

import com.example.voice_recorder.common.Resource
import com.example.voice_recorder.domain.repository.RecordsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteSingleItemByIdUseCase @Inject constructor(
    private val repository: RecordsRepository
) {
    operator fun invoke(id: Int) = flow {
        try {
            repository.deleteSavedRecordById(id)
            emit(Resource.Success<Unit>(Unit))
        } catch (e: Exception) {
            emit(Resource.Error<Unit>(e.message ?: "Can't delete this item"))
        }
    }
}