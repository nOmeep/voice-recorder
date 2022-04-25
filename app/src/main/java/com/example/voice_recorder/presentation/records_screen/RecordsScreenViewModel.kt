package com.example.voice_recorder.presentation.records_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voice_recorder.common.Constants.DEBUG_TAG
import com.example.voice_recorder.common.Resource
import com.example.voice_recorder.data.local.items.RecordItem
import com.example.voice_recorder.domain.use_cases.deletion.DeleteAllSavedItemsUseCase
import com.example.voice_recorder.domain.use_cases.deletion.DeleteSingleItemByIdUseCase
import com.example.voice_recorder.domain.use_cases.get_all_saved_records.GetAllSavedRecordsUseCase
import com.example.voice_recorder.domain.use_cases.insertion.InsertSingleItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RecordsScreenViewModel @Inject constructor(
    private val getAllSavedUseCase: GetAllSavedRecordsUseCase,
    private val deleteAllSavedUseCase: DeleteAllSavedItemsUseCase,
    private val saveRecordUseCase: InsertSingleItemUseCase,
    private val deleteSingleRecordByIdUseCase: DeleteSingleItemByIdUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RecordsListState())
    val state: State<RecordsListState> get() = _state

    init {
        fetchAllSavedRecords()
    }

    private fun fetchAllSavedRecords() = getAllSavedUseCase().onEach { result ->
        Log.d(DEBUG_TAG, "try to fetch saved data")
        when (result) {
            is Resource.Success -> {
                _state.value = RecordsListState(records = result.data ?: emptyList())
            }
            is Resource.Error -> {
                _state.value =
                    RecordsListState(error = result.errorMessage ?: "Something goes wrong")
            }
            is Resource.Loading -> {
                _state.value = RecordsListState(isLoading = true)
            }
        }
    }.launchIn(viewModelScope)

    fun saveSingleRecord(record: RecordItem) {
        runBlocking {
            saveRecordUseCase(record).launchIn(viewModelScope)
        }
        // мейби тут ошибка
        fetchAllSavedRecords()
    }
}