package com.example.mycasinoapplication.model

data class TCPPlayer(
    val cards: MutableList<Card>,
    val ante: Int,
    var playBet: Int,
    val pairsPlusBet: Int,
    val sixCardBet: Int,
    val isBanker: Boolean = false
) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun removeCard(card: Card) {
        cards.remove(card)
    }

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
//        return super.toString()
    }
}
