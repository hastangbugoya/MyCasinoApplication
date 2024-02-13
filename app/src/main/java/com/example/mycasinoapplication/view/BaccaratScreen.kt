package com.example.mycasinoapplication.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mycasinoapplication.viewmodel.BaccaratViewModel

@Composable
@coil.annotation.ExperimentalCoilApi
fun BaccaratScreen(viewModel: BaccaratViewModel) {
    val bankerHand by viewModel.bankerHand.collectAsState()
    val playerHand by viewModel.playerHand.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.Green).padding(8.dp)) {
            LazyRow() {
                Log.d("Meow", "banker: ${bankerHand.size} ${(bankerHand?.sumOf { it.value })?.mod(10)}")
                items(bankerHand) {
                    Log.d("Meow", "Banker ${it.getValueName()} ${it.suit}")
                    val painter = rememberImagePainter(data = it.cardImage)
                    Image(painter, "Banker ${it.getValueName()} ${it.suit}", modifier = Modifier.width(100.dp))
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.Green).padding(8.dp)) {
            LazyRow() {
                Log.d("Meow", "banker: ${playerHand.size} ${(playerHand.sumOf { it.value }).mod(10)}")
                items(playerHand) {
                    Log.d("Meow", "Player ${it.getValueName()} ${it.suit}")
                    val painter = rememberImagePainter(data = it.cardImage)
                    Image(painter, "Player ${it.getValueName()} ${it.suit}", modifier = Modifier.width(100.dp))
                }
            }
        }
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
