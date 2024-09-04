package com.example.practice3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practice3.ui.theme.Practice3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice3Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    // NavControllerを使ってナビゲーションを管理
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            // メイン画面
            composable("main") {
                MainContent(navController = navController)
            }
            // ScrollScreenの画面
            composable("scroll_screen") {
                val scrollScreen = ScrollScreen()
                scrollScreen.Content()
            }
            // ScrollTextScreenの画面
            composable("scroll_text_screen") {
                val scrollTextScreen = ScrollTextScreen()
                scrollTextScreen.Content()
            }
        }
    }
}

@Composable
fun MainContent(navController: NavHostController) {
    // Columnの配置を中央に設定
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ボタン間のスペースを追加
        Button(
            onClick = { navController.navigate("scroll_screen") },
            modifier = Modifier.padding(bottom = 16.dp) // 追加: ボタン間のスペース
        ) {
            Text("ScrollScreen")
        }

        Button(onClick = { navController.navigate("scroll_text_screen") }) {
            Text("ScrollTextScreen")
        }
    }
}
