package com.example.mycasinoapplication.view

import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.mycasinoapplication.viewmodel.BlackjackViewModel

@Composable
fun BlackjackScreen(viewModel: BlackjackViewModel) {
    Box(
        modifier = Modifier.background(Color(0xFF234D23)).fillMaxSize(),
    ) {
        DealerHand(viewModel = viewModel, height = 120)
    }
}

@Composable
fun DealerHand(viewModel: BlackjackViewModel, height: Int) {
    val dealer by viewModel.dealer.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth().background(
            Color(
                0xFF234D23,
            ),
        ),
    ) {
        Text(
            "Dealer",
            modifier = Modifier.fillMaxWidth()
                .background(Color.Transparent)
                .padding(8.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Red,
        )
        Box() {
            Log.d("Meow", "Dealer hand : ${dealer.cards.size}")
            LazyRow(modifier = Modifier.fillMaxWidth().padding(8.dp).height(height.dp)) {
                items(dealer.cards) {
                    Log.d("Meow", "Banker ${it.getValueName()} ${it.suit} ${it.showFront}")
                    val painter = rememberImagePainter(data = it.cardImage)
                    Column() {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(initialAlpha = 0.2f),
                        ) {
                            Image(
                                painter,
                                "Banker ${it.getValueName()} ${it.suit}",
                                modifier = Modifier.width(100.dp).padding(4.dp),
                            )
                        }
                    }
                }
            }
            FloatingActionButton(
                onClick = {
//                    viewModel.deal()
                    Log.d("Meow", "DEal!!!")
                },
                Modifier.align(Alignment.BottomEnd).padding(16.dp).size(70.dp),
                backgroundColor = Color.Blue,
                contentColor = Color.White,
                content = {
                    val hitIcon =
                        rememberImagePainter(data = R.drawable.baseline_slideshow_24)
                    Icon(
                        hitIcon,
                        contentDescription = "Show",
                        modifier = Modifier.height(50.dp).padding(8.dp),
                    )
                },
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 16.dp,
                ),
            )
        }
    }
}
