package com.example.mycasinoapplication.model

data class Card(val suit: Suits, val cardImage: Int, val value: Int) : java.io.Serializable {
    fun getValueName(): String {
        return when (val c: Int = value) {
            1 -> "Ace"
            in (2..10) -> "$c"
            11 -> "Jack"
            12 -> "Queen"
            else -> "King"
        }
    }
}
