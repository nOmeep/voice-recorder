package com.example.voice_recorder.presentation.records_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voice_recorder.R
import com.example.voice_recorder.data.local.items.RecordItem

@Composable
fun ExistingRecord(record: RecordItem) {
    Box(modifier = Modifier.padding(4.dp)) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                    contentDescription = "Play a record",
                    modifier = Modifier
                        .background(
                            color = Color.Cyan,
                            shape = CircleShape
                        )
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = record.name,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
fun ExistingRecordPreview() {
    ExistingRecord(
        record = RecordItem(
            createdAt = System.currentTimeMillis(),
            duration = System.currentTimeMillis(),
            name = "aboba"
        )
    )
}

