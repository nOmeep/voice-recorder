package com.example.voice_recorder.data.local.items

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voice_records_table")
data class RecordItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val filePath: String = "",
    val duration: Long = 0,
    val createdAt: Long = 0
)
