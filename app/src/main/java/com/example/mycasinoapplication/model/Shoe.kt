package com.example.mycasinoapplication.model

import com.example.mycasinoapplication.R

class Shoe(val myGame: Game = Game.BACCARAT) {
    private val deck = mutableListOf<Card>()
    val cardsInShoe: Int get() = deck.size
    fun drawCard(): Card {
        val index = (1..1000000).random().mod(deck.size)
        val card = deck.get(index)
        deck.removeAt(index)
        return card
    }

    fun loadShoe(decks: Int) {
        deck.clear()
        (1..decks).forEach {
            addDeck()
        }
    }

    private fun addDeck() {
        val kingValue = when (myGame) {
            Game.BACCARAT -> 0
            Game.BLACKJACK -> 10
            else -> 13
        }
        val queenValue = when (myGame) {
            Game.BACCARAT -> 0
            Game.BLACKJACK -> 10
            else -> 12
        }
        val jackValue = when (myGame) {
            Game.BACCARAT -> 0
            Game.BLACKJACK -> 10
            else -> 11
        }
        deck.add(Card(Suits.CLUB, R.drawable.clubs_ace, 1, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_2, 2, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_3, 3, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_4, 0, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_5, 5, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_6, 6, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_7, 7, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_8, 8, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_9, 9, false))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_10, 10, false))
        deck.add(Card(Suits.CLUB, R.drawable.c11b, jackValue, false))
        deck.add(Card(Suits.CLUB, R.drawable.c12b, queenValue, false))
        deck.add(Card(Suits.CLUB, R.drawable.c13b, kingValue, false))

        deck.add(Card(Suits.SPADE, R.drawable.spades_ace, 1, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_2, 2, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_3, 3, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_4, 4, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_5, 5, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_6, 6, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_7, 7, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_8, 8, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_9, 9, false))
        deck.add(Card(Suits.SPADE, R.drawable.spades_10, 10, false))
        deck.add(Card(Suits.SPADE, R.drawable.s11b, jackValue, false))
        deck.add(Card(Suits.SPADE, R.drawable.s12b, queenValue, false))
        deck.add(Card(Suits.SPADE, R.drawable.s13b, kingValue, false))

        deck.add(Card(Suits.HEART, R.drawable.hearts_ace, 1, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_2, 2, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_3, 3, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_4, 4, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_5, 5, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_6, 6, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_7, 7, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_8, 8, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_9, 9, false))
        deck.add(Card(Suits.HEART, R.drawable.hearts_10, 10, false))
        deck.add(Card(Suits.HEART, R.drawable.h11b, jackValue, false))
        deck.add(Card(Suits.HEART, R.drawable.h12b, queenValue, false))
        deck.add(Card(Suits.HEART, R.drawable.h13b, kingValue, false))

        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_ace, 1, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_2, 2, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_3, 3, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_4, 4, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_5, 5, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_6, 6, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_7, 7, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_8, 8, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_9, 9, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_10, 10, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.d11b, jackValue, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.d12b, queenValue, false))
        deck.add(Card(Suits.DIAMOND, R.drawable.d13b, kingValue, false))
    }
}
