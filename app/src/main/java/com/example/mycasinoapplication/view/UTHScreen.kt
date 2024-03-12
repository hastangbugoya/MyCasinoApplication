package com.example.mycasinoapplication.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mycasinoapplication.R
import com.example.mycasinoapplication.model.UTHPlayer
import com.example.mycasinoapplication.viewmodel.UTHViewModel

@Composable
fun UTHScreen(viewModel: UTHViewModel) {
    val dealer by viewModel.dealer.collectAsState()
    Box(
        modifier = Modifier.background(Color(0xFF234D23)).fillMaxSize(),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CommunityHand(viewModel, 140)
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                Log.d("Meow", "UTHScreen > display dealer")
                Column(modifier = Modifier.background(Color(0xFF193919)).padding(4.dp)) {
                    UTHPlayerHand(dealer, "DEALER")
                }
                Log.d("Meow", "UTHScreen > display players")
                Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    UTHPlayers(viewModel)
                }
            }
        }
        FloatingActionButton(
            onClick = {
                viewModel.deal()
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CommunityHand(viewModel: UTHViewModel, height: Int) {
    val community by viewModel.community.collectAsState()
    Column(
        modifier = Modifier
            .height(height = (height).dp)
            .fillMaxWidth().border(2.dp, Color.Yellow).background(Color(R.color.defaultgreen))
            .background(color = Color.Transparent, shape = RoundedCornerShape(16.dp))
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
    ) {
        Text(
            "Community",
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Yellow,
        )
        Box {
            LazyRow(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Log.d("Meow", "UTHScreen > community card count: ${community.size}")
                items(community) {
                    Log.d("Meow", "community ${it.getValueName()} ${it.suit}")
                    val painter = rememberImagePainter(data = it.cardImage)
                    Column() {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(initialAlpha = 0.2f),
                        ) {
                            Image(
                                painter,
                                "Banker ${it.getValueName()} ${it.suit}",
                                modifier = Modifier.fillParentMaxWidth(0.2f).padding(2.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UTHPlayerHand(uTHPlayer: UTHPlayer, label: String) {
    Log.d("Meow", "UTHScreen > player card count: ${uTHPlayer.cards.size}")
    LazyColumn() {
        items(uTHPlayer.cards) { card ->
            Column() {
                Text(label, fontSize = 12.sp, color = Color.Yellow)
                Box(
                    modifier = Modifier.fillMaxHeight().align(Alignment.CenterHorizontally),
                ) {
                    val painter = painterResource(card.cardImage)
                    Image(
                        painter,
                        "${card.getValueName()} ${card.suit}",
                        modifier = Modifier.height(120.dp).padding(4.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun UTHPlayers(viewModel: UTHViewModel) {
    val players by viewModel.players.collectAsState()
    Log.d("Meow", "UTHScreen > UTHPlayers > UTH players count: ${players.size}")
    LazyRow() {
        itemsIndexed(players) { index, uTHP ->
            Box {
                UTHPlayerHand(uTHPlayer = uTHP, "SEAT ${index + 1}")
            }
        }
    }
}

@Preview
@Composable
fun UTHPokerPreview() {
    val myViewModel = UTHViewModel()
    myViewModel.dealCommunityHand()
    myViewModel.deal()
    UTHScreen(myViewModel)
}
