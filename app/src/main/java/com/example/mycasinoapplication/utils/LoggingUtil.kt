package com.example.mycasinoapplication.utils

import android.util.Log
import com.example.mycasinoapplication.model.Game

class LoggingUtil {
    fun log(s: String, game: Game) {
        Log.d("${game}","${s}")
    }
}