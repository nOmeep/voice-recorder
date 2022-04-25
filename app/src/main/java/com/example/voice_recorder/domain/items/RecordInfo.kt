package com.example.voice_recorder.domain.items

import com.example.voice_recorder.data.local.items.RecordItem

data class RecordInfo(
    val filePath: String,
    val duration: Long,
    val createdAt: Long
)

fun RecordInfo.toRecordItem(name: String) = RecordItem(
    name = name,
    filePath = this.filePath,
    duration = this.duration,
    createdAt = this.createdAt
)