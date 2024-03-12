package com.example.mycasinoapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mycasinoapplication.model.BlackjackPlayer
import com.example.mycasinoapplication.model.BlackjackTable
import com.example.mycasinoapplication.model.Card
import com.example.mycasinoapplication.model.Game
import com.example.mycasinoapplication.model.Shoe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BlackjackViewModel : ViewModel() {
    private val _shoe = MutableStateFlow<Shoe>(Shoe(Game.THREE_CARD_POKER))
    val shoe = _shoe.asStateFlow()
    private var _dealer = MutableStateFlow<BlackjackPlayer>(BlackjackPlayer(mutableListOf(), 0))
    val dealer = _dealer.asStateFlow()
    private var _players = MutableStateFlow<MutableList<BlackjackPlayer>>(mutableListOf())
    val players = _players.asStateFlow()
    private var _tableType = MutableStateFlow<BlackjackTable>(BlackjackTable.TEN)
    val tableType = _tableType.asStateFlow()

    fun getTableType(): BlackjackTable = _tableType.value

    fun makeTable(type: BlackjackTable) {
        _tableType.value = type
    }

    fun deal() {
        resetShoe()
        dealDealerHand()
        dealPlayers(4)
    }

    fun resetShoe() {
        Log.d("Meow", "Blackjack > reset shoe")
        _shoe.value = Shoe(Game.BLACKJACK).apply {
            loadShoe(
                when (_tableType.value) {
                    BlackjackTable.TEN -> 2
                    else -> 5
                },
            )
        }
    }

    fun getBlackjackHandValue(list: MutableList<Card>): Int {
        var sum = 0
        list.sortedByDescending { it.value }.forEach {
            sum.plus(
                when {
                    it.value == 1 -> if (sum + 11 > 21) {
                        1
                    } else {
                        11
                    }
                    else -> it.value
                },
            )
        }
        return sum
    }

    fun MutableList<Card>.getValue(): Int {
        var sum = 0
        this.sortedByDescending { it.value }.forEach {
            sum.plus(
                when {
                    it.value == 1 -> if (sum + 11 > 21) {
                        1
                    } else {
                        11
                    }
                    else -> it.value
                },
            )
        }
        return sum
    }

    fun dealPlayerCard(): Card = _shoe.value.drawCard().apply { showFront = false }

    fun dealDealerHand() {
        val tempCards = mutableListOf<Card>().apply {
            add(_shoe.value.drawCard())
            _shoe.value.drawCard().apply { showFront = false }
        }
        _dealer.value = BlackjackPlayer(
            tempCards,
            tempCards.getValue(),
        )
    }

    fun dealPlayers(count: Int) {
        val tempList = mutableListOf<BlackjackPlayer>()
        (1..count).forEach {
            val tempCardList = mutableListOf(
                dealPlayerCard(),
                dealPlayerCard(),
            )
            tempList.add(
                BlackjackPlayer(
                    tempCardList,
                    tempCardList.getValue(),
                ),
            )
        }
        _players.value = tempList
    }
    fun hit(p: BlackjackPlayer) {
        _players.value.forEach {
            if (it == p) {
                it.cards.add(_shoe.value.drawCard())
            }
        }
        _players.value = players.value
    }
}
