package com.example.mycasinoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycasinoapplication.ui.theme.MyCasinoApplicationTheme
import com.example.mycasinoapplication.view.BaccaratScreen
import com.example.mycasinoapplication.viewmodel.BaccaratViewModel

class MainActivity : ComponentActivity() {
    val baccaratViewModel: BaccaratViewModel by lazy {
        BaccaratViewModel()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCasinoApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    BaccaratScreen(viewModel = baccaratViewModel)
//                    Greeting("Android")
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
        Greeting("Android")
    }
}
