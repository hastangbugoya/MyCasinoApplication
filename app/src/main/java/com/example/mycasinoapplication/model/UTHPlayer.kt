package com.example.mycasinoapplication.model

data class UTHPlayer(
    val cards: MutableList<Card>,
    val ante: Int = 0,
    var playBet: Int = 0,
    val tripsBet: Int = 0,
    val blindBet: Int = 0,
    val isBanker: Boolean = false
) {
    fun cardCount(): Int {
        return cards.size
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        cards.forEach {
            if (stringBuilder.isEmpty()) {
                stringBuilder.append("[${it.getValueName()} ${it.suit}] ")
            } else {
                stringBuilder.append(", [${it.getValueName()} ${it.suit}] ")
            }
        }
        return stringBuilder.toString()
    }
}
