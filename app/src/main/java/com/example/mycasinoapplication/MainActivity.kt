package com.example.mycasinoapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycasinoapplication.model.Game
import com.example.mycasinoapplication.ui.theme.MyCasinoApplicationTheme
import com.example.mycasinoapplication.view.BaccaratScreen
import com.example.mycasinoapplication.view.BlackjackScreen
import com.example.mycasinoapplication.view.ThreeCardPokerScreen
import com.example.mycasinoapplication.view.UTHScreen
import com.example.mycasinoapplication.viewmodel.BaccaratViewModel
import com.example.mycasinoapplication.viewmodel.BlackjackViewModel
import com.example.mycasinoapplication.viewmodel.ThreeCardPokerViewModel
import com.example.mycasinoapplication.viewmodel.UTHViewModel

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
    MyCasinoApplicationTheme {
        MyScaffolding()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyScaffolding() {
    val myContext = LocalContext.current
    val baccaratViewModel: BaccaratViewModel by lazy {
        BaccaratViewModel()
    }
    val threeCardPokerViewModel: ThreeCardPokerViewModel by lazy {
        ThreeCardPokerViewModel()
    }
    val blackjackViewModel: BlackjackViewModel by lazy {
        BlackjackViewModel()
    }
    val uTHViewModel: UTHViewModel by lazy {
        UTHViewModel()
    }
//    uTHViewModel.deal()
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
                    label = { Text(text = "3 Card") },
                )
                BottomNavigationItem(
                    icon = {
                        val iconPainter = painterResource(R.drawable.clubs)
                        Icon(painter = iconPainter, contentDescription = null, modifier = Modifier.size(32.dp, 32.dp).padding(8.dp))
                    },
                    selected = selectedGameTab == Game.UTH,
                    onClick = { selectedGameTab = Game.UTH },
                    label = { Text(text = "UTH") },
                )
                BottomNavigationItem(
                    icon = {
                        val iconPainter = painterResource(R.drawable.diamonds)
                        Icon(painter = iconPainter, contentDescription = null, modifier = Modifier.size(32.dp, 32.dp).padding(8.dp))
                    },
                    selected = selectedGameTab == Game.BLACKJACK,
                    onClick = { selectedGameTab = Game.BLACKJACK },
                    label = { Text(text = "Blackjack") },
                )
                // Add more BottomNavigationItem for additional tabs
            }
        },
        content = {
            // Switch between composables based on selectedTab
            Box(
                modifier = Modifier.fillMaxSize().padding(bottom = 56.dp),
            ) {
                when (selectedGameTab) {
                    Game.BLACKJACK -> BlackjackScreen(blackjackViewModel)
                    Game.BACCARAT -> BaccaratScreen(baccaratViewModel)
                    Game.THREE_CARD_POKER -> ThreeCardPokerScreen(threeCardPokerViewModel)
                    Game.UTH -> UTHScreen(viewModel = uTHViewModel)
                }
            }
        },
    )
}
