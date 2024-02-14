package com.example.mycasinoapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mycasinoapplication.R
import com.example.mycasinoapplication.model.Card
import com.example.mycasinoapplication.model.DrawRecepient
import com.example.mycasinoapplication.model.Suits
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BaccaratViewModel : ViewModel() {
    private val deck = mutableListOf<Card>()
    private var _playerTotal = MutableStateFlow<Int>(0)
    var playerTotal = _playerTotal.asStateFlow()
    private var _bankerTotal = MutableStateFlow<Int>(0)
    var bankerTotal = _bankerTotal.asStateFlow()
    private val _bankerHand = MutableStateFlow<MutableList<Card>>(mutableListOf())
    val bankerHand = _bankerHand.asStateFlow()
    private val _playerHand = MutableStateFlow<MutableList<Card>>(mutableListOf())
    val playerHand = _playerHand.asStateFlow()

    fun drawCard(recipient: DrawRecepient) {
        val index = (1..1000000).random().mod(deck.size)
        val card = deck.get(index)
        when (recipient) {
            DrawRecepient.BANKER -> {
                _bankerHand.value = _bankerHand.value.toMutableList().apply { add(card) }
                _bankerTotal.value = _bankerHand.value.sumOf { it.value }.mod(10)
            }
            DrawRecepient.PLAYER -> {
                _playerHand.value = _playerHand.value.toMutableList().apply { add(card) }
                _playerTotal.value = _playerHand.value.sumOf { it.value }.mod(10)
            }
        }
        deck.removeAt(index)
    }

    fun clearHands() {
        Log.d("Meow", "CLEAR!")
        _bankerHand.value = _bankerHand.value.toMutableList().apply { removeAll { true } }
        _playerHand.value = _playerHand.value.toMutableList().apply { removeAll { true } }
        Log.d("Meow", "clear > Playerhand count ${playerHand.value.size}")
        Log.d("Meow", "clear > Bankerhand count ${bankerHand.value.size}")
    }

    fun drawHands() {
        drawCard(DrawRecepient.PLAYER)
        drawCard(DrawRecepient.BANKER)
        drawCard(DrawRecepient.PLAYER)
        drawCard(DrawRecepient.BANKER)
        Log.d("Meow", "draw > Playerhand count ${playerHand.value.size}")
        Log.d("Meow", "draw > Bankerhand count ${bankerHand.value.size}")
        Log.d("Meow", "draw > Deck count ${deck.size}")
    }

    init {
        loadShoe(5)
    }
    fun loadShoe(deckCount: Int) {
        deck.clear()
        for (i in 1..deckCount) {
            addDeck()
        }
    }

    fun hit(hitter: DrawRecepient) {
        when (hitter) {
            DrawRecepient.BANKER -> {}
            DrawRecepient.PLAYER -> {}
        }
    }

    private fun addDeck() {
        deck.add(Card(Suits.CLUB, R.drawable.clubs_ace, 1))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_2, 2))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_3, 3))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_4, 4))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_5, 5))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_6, 6))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_7, 7))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_8, 8))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_9, 9))
        deck.add(Card(Suits.CLUB, R.drawable.clubs_10, 10))
        deck.add(Card(Suits.CLUB, R.drawable.c11b, 0))
        deck.add(Card(Suits.CLUB, R.drawable.c12b, 0))
        deck.add(Card(Suits.CLUB, R.drawable.c13b, 0))

        deck.add(Card(Suits.SPADE, R.drawable.spades_ace, 1))
        deck.add(Card(Suits.SPADE, R.drawable.spades_2, 2))
        deck.add(Card(Suits.SPADE, R.drawable.spades_3, 3))
        deck.add(Card(Suits.SPADE, R.drawable.spades_4, 4))
        deck.add(Card(Suits.SPADE, R.drawable.spades_5, 5))
        deck.add(Card(Suits.SPADE, R.drawable.spades_6, 6))
        deck.add(Card(Suits.SPADE, R.drawable.spades_7, 7))
        deck.add(Card(Suits.SPADE, R.drawable.spades_8, 8))
        deck.add(Card(Suits.SPADE, R.drawable.spades_9, 9))
        deck.add(Card(Suits.SPADE, R.drawable.spades_10, 10))
        deck.add(Card(Suits.SPADE, R.drawable.s11b, 0))
        deck.add(Card(Suits.SPADE, R.drawable.s12b, 0))
        deck.add(Card(Suits.SPADE, R.drawable.s13b, 0))

        deck.add(Card(Suits.HEART, R.drawable.hearts_ace, 1))
        deck.add(Card(Suits.HEART, R.drawable.hearts_2, 2))
        deck.add(Card(Suits.HEART, R.drawable.hearts_3, 3))
        deck.add(Card(Suits.HEART, R.drawable.hearts_4, 4))
        deck.add(Card(Suits.HEART, R.drawable.hearts_5, 5))
        deck.add(Card(Suits.HEART, R.drawable.hearts_6, 6))
        deck.add(Card(Suits.HEART, R.drawable.hearts_7, 7))
        deck.add(Card(Suits.HEART, R.drawable.hearts_8, 8))
        deck.add(Card(Suits.HEART, R.drawable.hearts_9, 9))
        deck.add(Card(Suits.HEART, R.drawable.hearts_10, 10))
        deck.add(Card(Suits.HEART, R.drawable.h11b, 0))
        deck.add(Card(Suits.HEART, R.drawable.h12b, 0))
        deck.add(Card(Suits.HEART, R.drawable.h13b, 0))

        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_ace, 1))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_2, 2))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_3, 3))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_4, 4))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_5, 5))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_6, 6))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_7, 7))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_8, 8))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_9, 9))
        deck.add(Card(Suits.DIAMOND, R.drawable.diamonds_10, 10))
        deck.add(Card(Suits.DIAMOND, R.drawable.d11b, 0))
        deck.add(Card(Suits.DIAMOND, R.drawable.d12b, 0))
        deck.add(Card(Suits.DIAMOND, R.drawable.d13b, 0))
    }
}
