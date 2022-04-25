package com.example.voice_recorder.presentation.records_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.voice_recorder.data.local.items.RecordItem
import com.example.voice_recorder.presentation.record_controller.RecordController
import com.example.voice_recorder.presentation.records_screen.RecordsScreenViewModel

@Composable
fun MainScreen(
    requestPermission: () -> Unit,
    recordController: RecordController,
    checkPermission: () -> Boolean,
    viewModel: RecordsScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        RecordsList(
            state = state.value
        )
        Spacer(
            modifier = Modifier
                .height(4.dp)
        )
        StartRecordPanel(
            requestPermission,
            checkPermission,
            recordController
        ) { item: RecordItem -> viewModel.saveSingleRecord(item) }
    }
}