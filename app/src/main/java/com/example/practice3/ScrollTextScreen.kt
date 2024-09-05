package com.example.practice3

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

class ScrollTextScreen {
    val quotes = listOf(
        "明日を作り出す唯一の限界は、今日の私たちの疑いである。",
        "鉄が熱いうちに打てるのを待たず、打つことで熱くせよ。",
        "未来は、自分の夢の美しさを信じる人々に属する。",
        "どれだけゆっくり進んでも、止まりさえしなければ問題ない。",
        "未来を予測する最良の方法は、それを創造することである。",
        "人生における最大の栄光は、一度も失敗しないことではなく、失敗するたびに立ち上がることである。",
        "自分を信じなければ、何も始まらない。",
        "今日できることを、明日に延ばすな。",
        "失敗を恐れるより、何もしないことを恐れよ。",
        "不可能を可能にするのは、挑戦する勇気である。",
        "自分を変えれば、世界も変わる。",
        "真実の幸福は、他人の幸福を願う心から生まれる。",
        "学び続けること、それが人生の目的である。",
        "人間は考えた通りの存在になる。",
        "逆境に立ち向かうことで、強さが生まれる。",
        "過去を忘れ、未来を夢見て、今を生きよ。",
        "言葉は刃物のようなもの、使い方次第で人を救うことも傷つけることもできる。",
        "希望とは、夜明け前の光のようなもの。",
        "自分を許し、他人を許すことで心の平和が訪れる。",
        "夢を追いかけること、それが生きる力になる。",
        "小さな一歩でも、続けることで大きな変化をもたらす。",
        "人生の意味は、自分で見つけるものである。",
        "努力は報われる、ただし、その報いは必ずしもすぐに訪れるとは限らない。",
        "失敗は成功の母、何度でも挑戦せよ。",
        "感謝の気持ちを忘れずに、日々を過ごす。",
        "他人と比べるのではなく、昨日の自分と比べる。",
        "人は生まれながらにして自由である。",
        "成長とは、昨日より少しでも前進していることである。",
        "幸せは外に求めるものではなく、内に見つけるものである。",
        "勇気とは、恐怖に立ち向かう力である。"
    )

    val backgroundImages = listOf(
        R.drawable.wasitu,
        R.drawable.beach,
        R.drawable.bg_screen3
    )

    @Composable
    fun Content() {
        var currentIndex by remember { mutableStateOf(0) }
        var nextIndex by remember { mutableStateOf((currentIndex + 1) % quotes.size) }
        var dragOffset by remember { mutableStateOf(0f) }
        var scrollDirection by remember { mutableStateOf(0f) }
        var directionLocked by remember { mutableStateOf(false) }
        var selectedBackground by remember { mutableStateOf(0) }
        val screenHeight = 800f

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            // 指を離したときに次の文字列に切り替え
                            if (dragOffset > screenHeight / 2) {
                                currentIndex = if (currentIndex > 0) currentIndex - 1 else quotes.size - 1
                            } else if (dragOffset < -screenHeight / 2) {
                                currentIndex = (currentIndex + 1) % quotes.size
                            }
                            dragOffset = 0f  // オフセットをリセット
                            directionLocked = false  // 方向ロック解除
                        },
                        onDrag = { _, dragAmount ->
                            dragOffset += dragAmount.y  // ドラッグ量をオフセットに反映

                            if (!directionLocked) {
                                scrollDirection = dragAmount.y
                                nextIndex = if (scrollDirection > 0) {
                                    if (currentIndex > 0) currentIndex - 1 else quotes.size - 1
                                } else {
                                    (currentIndex + 1) % quotes.size
                                }
                                directionLocked = true  // 方向をロック
                            }
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            // 背景画像の設定
            Image(
                painter = painterResource(id = backgroundImages[selectedBackground]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // 現在のテキストの表示（指の動きに合わせてフェードアウト）
            Text(
                text = quotes[currentIndex],
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .graphicsLayer(
                        translationY = dragOffset,
                        alpha = 1f - abs(dragOffset) / (screenHeight / 2)
                    )
            )

            // 次のテキストの表示（フェードインして画面中央に表示）
            Text(
                text = quotes[nextIndex],
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .graphicsLayer(
                        translationY = if (dragOffset > 0) dragOffset - screenHeight else dragOffset + screenHeight,
                        alpha = abs(dragOffset) / (screenHeight / 2)
                    )
            )

            // 背景画像を切り替えるボタン
            Button(
                onClick = {
                    // 背景画像を切り替える
                    selectedBackground = (selectedBackground + 1) % backgroundImages.size
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text("背景画像を変更")
            }
        }
    }
}
