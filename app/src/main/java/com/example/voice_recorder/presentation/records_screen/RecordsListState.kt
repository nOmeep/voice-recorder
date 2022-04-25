package com.example.voice_recorder.presentation.records_screen

import com.example.voice_recorder.data.local.items.RecordItem

data class RecordsListState(
    val isLoading: Boolean = false,
    val records: List<RecordItem> = emptyList(),
    val error: String = ""
)