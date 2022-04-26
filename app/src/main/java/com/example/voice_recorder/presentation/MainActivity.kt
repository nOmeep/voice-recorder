package com.example.voice_recorder.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.voice_recorder.presentation.record_controller.RecordController
import com.example.voice_recorder.presentation.records_screen.components.MainScreen
import com.example.voice_recorder.presentation.ui.theme.Voice_RecorderTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var recordController: RecordController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Voice_RecorderTheme {
                MainScreen(
                    {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.RECORD_AUDIO),
                            777
                        )
                    },
                    recordController,
                    {
                        val locationPermission = ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                        locationPermission == PackageManager.PERMISSION_GRANTED
                    }
                )
            }
        }
    }
}