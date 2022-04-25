package com.example.voice_recorder.presentation.record_controller

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import com.example.voice_recorder.common.Constants.DEBUG_TAG
import com.example.voice_recorder.domain.items.RecordInfo

class RecordController(
    private val context: Context
) {
    private var mediaRecorder: MediaRecorder? = null

    private var startedTime: Long = 0L
    private var duration: Long = 0L
    private var filePath: String? = null

    fun start() {
        Log.d(DEBUG_TAG, "Started")
        val mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
        startedTime = System.currentTimeMillis()
        filePath = "${context.externalCacheDirs[0].absolutePath}/${startedTime}.wav"

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder.setOutputFile(filePath)
        mediaRecorder.prepare()
        mediaRecorder.start()
        this.mediaRecorder = mediaRecorder
    }

    fun isAudioRecording() = mediaRecorder != null

    fun stop(): RecordInfo {
        Log.d(DEBUG_TAG, "Stopped")
        duration = System.currentTimeMillis() - startedTime
        try {
            mediaRecorder?.stop()
            mediaRecorder?.release()
        } catch (e: Exception) {
            Log.d(DEBUG_TAG, "ААААСУЖДАЮ")
        }
        mediaRecorder = null

        return RecordInfo(
            filePath = filePath!!,
            duration = duration,
            createdAt = startedTime
        )
    }
}