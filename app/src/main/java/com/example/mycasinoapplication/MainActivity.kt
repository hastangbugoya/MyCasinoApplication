package com.example.mycasinoapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycasinoapplication.model.Game
import com.example.mycasinoapplication.ui.theme.MyCasinoApplicationTheme
import com.example.mycasinoapplication.view.BaccaratScreen
import com.example.mycasinoapplication.view.ThreeCardPokerScreen
import com.example.mycasinoapplication.viewmodel.BaccaratViewModel
import com.example.mycasinoapplication.viewmodel.ThreeCardPokerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCasinoApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        MyScaffolding()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val baccaratViewModel = BaccaratViewModel()
    val threeCardPokerViewModel = ThreeCardPokerViewModel()
    threeCardPokerViewModel.deal()
    MyCasinoApplicationTheme {
        MyScaffolding()
//        BaccaratScreen(viewModel = baccaratViewModel)
//        ThreeCardPokerScreen(viewModel = threeCardPokerViewModel)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyScaffolding() {
    val baccaratViewModel: BaccaratViewModel by lazy {
        BaccaratViewModel()
    }
    val threeCardPokerViewModel: ThreeCardPokerViewModel by lazy {
        ThreeCardPokerViewModel()
    }
    var selectedGameTab by remember { mutableStateOf(Game.BACCARAT) }
    Scaffold(
//        topBar = {
//            // Replace the comment with your top bar composable
//            TopAppBar(title = { Text(text = "Title Bar") })
//        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = {
                        val iconPainter = painterResource(R.drawable.spades)
                        Icon(painter = iconPainter, contentDescription = null, modifier = Modifier.size(32.dp, 32.dp).padding(8.dp))
                    },
                    selected = selectedGameTab == Game.BACCARAT,
                    onClick = { selectedGameTab = Game.BACCARAT },
                    label = { Text(text = "Baccarat") },
                )
                BottomNavigationItem(
                    icon = {
                        val iconPainter = painterResource(R.drawable.heart)
                        Icon(painter = iconPainter, contentDescription = null, modifier = Modifier.size(32.dp, 32.dp).padding(8.dp))
                    },
                    selected = selectedGameTab == Game.THREE_CARD_POKER,
                    onClick = { selectedGameTab = Game.THREE_CARD_POKER },
                    label = { Text(text = "3 Card Poker") },
                )
                // Add more BottomNavigationItem for additional tabs
            }
        },
        content = {
            // Switch between composables based on selectedTab
            when (selectedGameTab) {
                Game.BACCARAT -> BaccaratScreen(baccaratViewModel)
                Game.THREE_CARD_POKER -> ThreeCardPokerScreen(threeCardPokerViewModel)
            }
        },
    )
}
