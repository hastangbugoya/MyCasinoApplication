package com.example.mycasinoapplication.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.mycasinoapplication.R
import com.example.mycasinoapplication.model.DrawRecepient
import com.example.mycasinoapplication.viewmodel.BaccaratViewModel

@Composable
@coil.annotation.ExperimentalCoilApi
fun BaccaratScreen(viewModel: BaccaratViewModel) {
    val bankerHand by viewModel.bankerHand.collectAsState()
    val playerHand by viewModel.playerHand.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Hand(viewModel, recipient = DrawRecepient.BANKER, height = 200)
        Hand(viewModel, recipient = DrawRecepient.PLAYER, height = 200)
        Button(
            onClick = {
                viewModel.clearHands()
                viewModel.drawHands()
            },
            modifier = Modifier.fillMaxWidth()
                .height(100.dp)
                .padding(8.dp),
        ) {
            Text(text = "DEAL")
        }
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
    Column(modifier = Modifier.height(height = (height).dp).fillMaxWidth().background(Color.Green)) {
        Text(
            recipient.toString() + " " + when (recipient) {
                DrawRecepient.BANKER -> bankerTotal.toString()
                DrawRecepient.PLAYER -> playerTotal.toString()
            },
            modifier = Modifier.fillMaxWidth()
                .background(Color.Transparent),
            fontSize = 32.sp,
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
            if (hand.size > 0) {
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
    Row(modifier = Modifier.fillMaxWidth()) {
    }
}
