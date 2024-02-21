package com.example.mycasinoapplication.model

data class BlackjackPlayer(
    val cards: MutableList<Card>,
    var value: Int,
) {
    fun hit(card: Card, showFront: Boolean) {
        cards.add(card.apply { this.showFront = showFront })
    }
}
