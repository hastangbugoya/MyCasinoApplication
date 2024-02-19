package com.example.mycasinoapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mycasinoapplication.model.Card
import com.example.mycasinoapplication.model.Game
import com.example.mycasinoapplication.model.Shoe
import com.example.mycasinoapplication.model.TCPPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThreeCardPokerViewModel() : ViewModel() {
    private val deck = mutableListOf<Card>()
    private var _tableMin: Int = 5
    val tableMin: Int get() = _tableMin
    private var _tableMax: Int = 300
    val tableMax: Int get() = _tableMax
    private var _bonusMin: Int = 100
    val bonusMin: Int get() = _bonusMax
    private var _bonusMax: Int = 100
    val bonusMax: Int get() = _bonusMax
    private val _shoe = MutableStateFlow<Shoe>(Shoe(Game.THREE_CARD_POKER))
    val shoe = _shoe.asStateFlow()
    private val _players = MutableStateFlow<MutableList<TCPPlayer>>(mutableListOf<TCPPlayer>())
    val players = _players.asStateFlow()
    private val _banker = MutableStateFlow<TCPPlayer>(TCPPlayer(mutableListOf(), 0, 0, 0, 0, false))
    val banker = _banker.asStateFlow()

    fun resetShoe() {
        Log.d("Meow", "3 card > reset shoe")
        _shoe.value = Shoe(Game.THREE_CARD_POKER).apply {
            loadShoe(1)
        }
    }

    fun dealDealerHand() {
        Log.d("Meow", "3 card > deal dealer hand")
        _banker.value = TCPPlayer(mutableListOf<Card>().apply {
            add(_shoe.value.drawCard())
            add(_shoe.value.drawCard())
            add(_shoe.value.drawCard())
        },0,0,0,0, true)
    }

    fun deal() {
        Log.d("Meow", "3 card > deal")
        resetShoe()
        dealDealerHand()
        _players.value = mutableListOf<TCPPlayer>().apply {
            clear()
            Log.d("Meow", "3 card > deal after players clear: $size")
            // 1
            add(
                TCPPlayer(
                    mutableListOf(
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                    ),
                    (5..300).random(),
                    0,
                    5,
                    5,
                ),
            )
            // 2
            add(
                TCPPlayer(
                    mutableListOf(
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                    ),
                    (5..300).random(),
                    0,
                    5,
                    5,
                ),
            )
            // 3
            add(
                TCPPlayer(
                    mutableListOf(
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                    ),
                    (5..300).random(),
                    0,
                    5,
                    5,
                ),
            )
            // 4
            add(
                TCPPlayer(
                    mutableListOf(
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                    ),
                    (5..300).random(),
                    0,
                    5,
                    5,
                ),
            )
            // 5
            add(
                TCPPlayer(
                    mutableListOf(
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                    ),
                    (5..300).random(),
                    0,
                    5,
                    5,
                ),
            )
            // 6
            add(
                TCPPlayer(
                    mutableListOf(
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                        _shoe.value.drawCard(),
                    ),
                    (5..300).random(),
                    0,
                    5,
                    5,
                ),
            )
            Log.d("Meow", "3 card > deal after players deal: $size")
        }
        Log.d("Meow", "3 card > deal after _players deal: ${_players.value.size}")
    }
}
