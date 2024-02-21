package com.example.mycasinoapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mycasinoapplication.model.Card
import com.example.mycasinoapplication.model.Game
import com.example.mycasinoapplication.model.Shoe
import com.example.mycasinoapplication.model.UTHPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UTHViewModel : ViewModel() {
    private var _anteMin: Int = 5
    val tableMin: Int get() = _anteMin
    private var _anteMax: Int = 300
    val anteMax: Int get() = _anteMax
    private var _tripsMin: Int = 1
    val tripsMin: Int get() = _tripsMin
    private val _shoe = MutableStateFlow<Shoe>(Shoe(Game.UTH))
    val shoe = _shoe.asStateFlow()
    private val _players = MutableStateFlow<MutableList<UTHPlayer>>(mutableListOf<UTHPlayer>())
    val players = _players.asStateFlow()
    private val _dealer = MutableStateFlow<UTHPlayer>(UTHPlayer(mutableListOf(), 0, 0, 0, 0, false))
    val dealer = _dealer.asStateFlow()
    private val _community = MutableStateFlow<MutableList<Card>>(mutableListOf())
    val community = _community.asStateFlow()

    fun resetShoe() {
        Log.d("Meow", "UTH > reset shoe")
        _shoe.value = Shoe(Game.UTH).apply {
            loadShoe(1)
        }
    }

    fun dealCommunityHand() {
        Log.d("Meow", "UTH > deal dealer hand")
        _community.value = mutableListOf<Card>(
            _shoe.value.drawCard(),
            _shoe.value.drawCard(),
            _shoe.value.drawCard(),
            _shoe.value.drawCard(),
            _shoe.value.drawCard(),
        )
    }

    fun dealDealer() {
        _dealer.value = UTHPlayer(
            mutableListOf(
                _shoe.value.drawCard(),
                _shoe.value.drawCard(),
            ),
            (5..300).random(),
            0,
            5,
            5,
        )
    }

    fun deal() {
        Log.d("Meow", "UTH > deal")
        resetShoe()
        dealCommunityHand()
        dealDealer()
        _players.value = mutableListOf<UTHPlayer>(
            UTHPlayer(
                mutableListOf(
                    _shoe.value.drawCard(),
                    _shoe.value.drawCard(),
                ),
                (5..300).random(),
                0,
                5,
                5,
            ),
            UTHPlayer(
                mutableListOf(
                    _shoe.value.drawCard(),
                    _shoe.value.drawCard(),
                ),
                (5..300).random(),
                0,
                5,
                5,
            ),
            UTHPlayer(
                mutableListOf(
                    _shoe.value.drawCard(),
                    _shoe.value.drawCard(),
                ),
                (5..300).random(),
                0,
                5,
                5,
            ),
            UTHPlayer(
                mutableListOf(
                    _shoe.value.drawCard(),
                    _shoe.value.drawCard(),
                ),
                (5..300).random(),
                0,
                5,
                5,
            ),
            UTHPlayer(
                mutableListOf(
                    _shoe.value.drawCard(),
                    _shoe.value.drawCard(),
                ),
                (5..300).random(),
                0,
                5,
                5,
            ),
            UTHPlayer(
                mutableListOf(
                    _shoe.value.drawCard(),
                    _shoe.value.drawCard(),
                ),
                (5..300).random(),
                0,
                5,
                5,
            ),
        )
        Log.d("Meow", "UTH > deal after _players deal: ${_players.value.size}")
    }
}
