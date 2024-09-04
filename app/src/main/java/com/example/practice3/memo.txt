package com.example.practice3

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import com.example.practice3.R

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


    @Composable
    fun Content() {
        // 現在の名言のインデックスを管理する
        var currentIndex by remember { mutableStateOf(0) }

        // 画面全体のレイアウト
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    // タッチされたときに次の名言へ
                    currentIndex = (currentIndex + 1) % quotes.size
                },
            contentAlignment = Alignment.Center
        ) {
            // 背景画像の設定
            Image(
                painter = painterResource(id = R.drawable.bg_screen3),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // 画像をディスプレイ全体に広げる
            )

            // 名言のフェードイン表示
            Crossfade(targetState = currentIndex) { index ->
                // 影となるテキスト
                Text(
                    text = quotes[index],
                    color = Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier.offset(2.dp, 2.dp)
                )
                // 本来のテキスト
                Text(
                    text = quotes[index],
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}
