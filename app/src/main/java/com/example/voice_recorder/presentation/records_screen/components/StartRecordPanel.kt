package com.example.voice_recorder.presentation.records_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.voice_recorder.R
import com.example.voice_recorder.data.local.items.RecordItem
import com.example.voice_recorder.domain.items.toRecordItem
import com.example.voice_recorder.presentation.record_controller.RecordController

@Composable
fun StartRecordPanel(
    requestPermission: () -> Unit,
    permissionGranted: () -> Boolean,
    recordController: RecordController,
    saveItem: (item: RecordItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            )
            .background(Color.White)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .background(
                    color = Color.Red,
                    shape = CircleShape
                )
                .clickable {
                    if (permissionGranted.invoke()) {
                        if (recordController.isAudioRecording()) {
                            val itemToSave = recordController
                                .stop()
                                .toRecordItem("sus")
                            saveItem.invoke(itemToSave)
                        } else {
                            recordController.start()
                        }
                    } else {
                        requestPermission.invoke()
                    }
                }
                .size(50.dp),
            painter = painterResource(id = R.drawable.ic_stop_icon),
            contentDescription = "Start recording button"
        )
    }
}