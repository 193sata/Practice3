package com.example.practice3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer


class ScrollScreen {
    @Composable
    fun Content() {
        // スクロール状態を記録するためのScrollState
        val scrollState = rememberScrollState()

        // スクロール範囲に応じて背景画像の比率を計算
        val imageIndex = (scrollState.value / 1000f).toInt() % 4 // 4つの画像でループ
        val imageAlpha = (scrollState.value % 1000f) / 1000f

        // 背景画像のリスト
        val images = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // 現在の画像を表示
            Image(
                painter = painterResource(id = images[imageIndex]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = 1f - imageAlpha } // 現在の画像の透明度を設定
            )

            // 次の画像をフェードインさせる
            Image(
                painter = painterResource(id = images[(imageIndex + 1) % images.size]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = imageAlpha } // 次の画像の透明度を設定
            )

            // スクロール可能な空白の領域を作成（テキストなし）
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp)
            ) {
                // スクロール領域を空白にするためのSpacer（50個分）
                Spacer(modifier = Modifier.height(5000.dp))
            }
        }
    }
}
