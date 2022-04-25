package com.example.voice_recorder.common

fun Long.millisToDuration(): String {
    val days = this / (1000 * 60 * 60 * 24)
    val hours = (this - (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
    val minutes =
        (this - (this - (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) - this / (1000 * 60 * 60 * 24)) / (1000 * 60)
    val seconds =
        this - (this - (this - (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) - this / (1000 * 60 * 60 * 24)) / (1000 * 60) - (this - (1000 * 60 * 60 * 24)) / (1000 * 60 * 60) - this / (1000 * 60 * 60 * 24)

    return StringBuilder().apply {
        if (days != 0L) {
            if (days / 10 == 0L) {
                append("0$days:")
            } else {
                append("$days:")
            }
        }
        if (hours == 0L) {
            append("00:")
        } else {
            if (hours / 10 == 0L) {
                append("0$hours:")
            } else {
                append("$hours:")
            }
        }
        if (minutes == 0L) {
            append("00:")
        } else {
            if (minutes / 10 == 0L) {
                append("0$minutes:")
            } else {
                append("$minutes:")
            }
        }
        if (seconds == 0L) {
            append("00")
        } else {
            if (seconds / 10 == 0L) {
                append("0$seconds")
            } else {
                append("$seconds")
            }
        }
    }.toString()
}