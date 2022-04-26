package com.example.voice_recorder.presentation.records_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.voice_recorder.presentation.records_screen.RecordsListState

@Composable
fun RecordsList(state: RecordsListState) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.records) { item ->
                ExistingRecord(
                    record = item
                )
            }
        }
        when {
            state.error.isNotBlank() -> {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        )
                        .align(Alignment.Center),
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                )
            }
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            state.records.isEmpty() -> {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        )
                        .align(Alignment.Center),
                    text = "No records yet",
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}