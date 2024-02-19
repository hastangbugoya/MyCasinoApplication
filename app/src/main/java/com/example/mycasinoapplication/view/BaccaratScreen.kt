package com.example.mycasinoapplication.view

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mycasinoapplication.R
import com.example.mycasinoapplication.model.BaccaratBet
import com.example.mycasinoapplication.model.BaccaratBets
import com.example.mycasinoapplication.model.DrawRecepient
import com.example.mycasinoapplication.viewmodel.BaccaratViewModel

@Composable
@coil.annotation.ExperimentalCoilApi
fun BaccaratScreen(viewModel: BaccaratViewModel) {
    val context = LocalContext.current
    val bankerHand by viewModel.bankerHand.collectAsState()
    val playerHand by viewModel.playerHand.collectAsState()
    val deckCount by viewModel.deckCount.collectAsState()
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF234D23)))
    {
        Hand(viewModel, recipient = DrawRecepient.BANKER, height = 200)
        Hand(viewModel, recipient = DrawRecepient.PLAYER, height = 200)
        if (bankerHand.size > 0) {
            Bonus(viewModel)
        }
        Button(
            onClick = {
                viewModel.clearHands()
                if (viewModel.deckCount.value < 20) {
                    Toast.makeText(context, "Deck less that 20, Re-shoeing", Toast.LENGTH_LONG).show()
                    viewModel.loadShoe(5)
                }
                viewModel.drawHands()
            },
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
                .padding(8.dp),
        ) {
            Text(text = "DEAL")
        }
        Text(text = deckCount.toString() + " cards left", modifier = Modifier.padding(horizontal = 8.dp))
    }
}

@Composable
fun Hand(viewModel: BaccaratViewModel, recipient: DrawRecepient, height: Int) {
    val hand by when (recipient) {
        DrawRecepient.BANKER -> viewModel.bankerHand.collectAsState()
        else -> viewModel.playerHand.collectAsState()
    }
    val playerTotal by viewModel.playerTotal.collectAsState()
    val bankerTotal by viewModel.bankerTotal.collectAsState()
    Column(
        modifier = Modifier.height(height = (height).dp).fillMaxWidth()) {
        Text(
            recipient.toString() + " " + when (recipient) {
                DrawRecepient.BANKER -> bankerTotal.toString()
                DrawRecepient.PLAYER -> playerTotal.toString()
            },
            modifier = Modifier.fillMaxWidth()
                .background(Color.Transparent)
                .padding(8.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Red,
        )
        Box() {
            LazyRow(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Log.d("Meow", "banker: ${hand.size} ${(hand.sumOf { it.value }).mod(10)}")
                items(hand) {
                    Log.d("Meow", "Banker ${it.getValueName()} ${it.suit}")
                    val painter = rememberImagePainter(data = it.cardImage)
                    Column() {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(initialAlpha = 0.2f),
                        ) {
                            Image(
                                painter,
                                "Banker ${it.getValueName()} ${it.suit}",
                                modifier = Modifier.width(100.dp),
                            )
                        }
                    }
                }
            }
            Log.d("Meow", "$recipient card count ${hand.size}")
            if (hand.size in 1..2) {
                FloatingActionButton(
                    onClick = {
                        viewModel.drawCard(recipient)
                    },
                    Modifier.align(Alignment.BottomEnd).padding(16.dp),
                    backgroundColor = Color.Blue,
                    contentColor = Color.White,
                    content = {
                        val hitIcon =
                            rememberImagePainter(data = R.drawable.baseline_plus_one_24)
                        Icon(
                            hitIcon,
                            contentDescription = "Hit",
                            modifier = Modifier.height(50.dp).padding(8.dp),
                        )
                    },
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 16.dp,
                    ),
                )
            }
        }
    }
}

@Composable
fun Bonus(viewModel: BaccaratViewModel) {
//    val bets: List<BaccaratBets> = enumValues<BaccaratBets>().toList()
    val bankerHand = viewModel.bankerHand.collectAsState()
    val bets = mutableListOf<BaccaratBet>()
    enumValues<BaccaratBets>().filter { !it.manualAdd }.forEach {
        bets.add(BaccaratBet(it, (it.min10..it.max10).random()))
    }
    when ((1..1000).random().mod(2) == 0) {
        true -> bets.add(BaccaratBet(BaccaratBets.BANKER_WINS, (BaccaratBets.BANKER_WINS.min10..BaccaratBets.BANKER_WINS.max10).random()))
        else -> bets.add(BaccaratBet(BaccaratBets.PLAYER_WINS, (BaccaratBets.PLAYER_WINS.min10..BaccaratBets.PLAYER_WINS.max10).random()))
    }
    LazyRow() {
        items(bets) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    it.betType.name,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFFFC107),
                )
                Text(
                    "${it.betType.odds} to 1",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color(0xFFFFC107),
                )
                val betAmount = (it.betType.min10..it.betType.max10).random()
                Text(
                    betAmount.toString(),
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFFFC107),
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                )
                val payout = betAmount * it.betType.odds
                Text(
                    payout.toString(),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 8.sp,
                    color = Color(0xFF8BC34A),
                )
            }
        }
    }
}
