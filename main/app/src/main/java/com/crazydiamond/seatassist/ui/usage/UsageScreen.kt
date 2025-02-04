package com.crazydiamond.seatassist.ui.usage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crazydiamond.seatassist.R
import com.crazydiamond.seatassist.data.UsageData
import com.crazydiamond.seatassist.ui.components.SubText
import com.crazydiamond.seatassist.ui.components.fontsBold
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun UsageScreen(
    onNavigationClick: () -> Unit
) {
    Scaffold(
        topBar = { UsageTopBar(onNavigationClick = onNavigationClick) },
        bottomBar = { Spacer(
            Modifier
                .navigationBarsHeight()
                .fillMaxWidth()) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
    )
    { contentPadding ->
        UsageCardView(
            contentPadding = contentPadding,
            cardsList = listOf(
                UsageData(
                    "Main Screen",
                    "In this section, you determine the placement of the sheets.\n" +
                            "Single tap to add a seat, double tap to delete the tapped seat..",
                    R.drawable.main_screen
                ),
                UsageData(
                    "Members",
                    "This is where you can register as a member.\n" +
                            "You can add members one by one by clicking the button on the upper right, or you can set the number of members at once by clicking \"Number of members\".",
                    R.drawable.member_screen
                ),
                UsageData(
                    "Member's Number",
                    "On this screen, the number of members can be determined in bulk. Note, however, that the information you have entered will be initialized.",
                    R.drawable.number_screen
                ),
                UsageData(
                    "Custom",
                    "Here, you can change the color and size of the seats to be placed.\n" +
                            "Once you make a decision here, the changes will be applied until you do the customization again.",
                    R.drawable.custom_screen
                ),
                UsageData(
                    "Lottery",
                    "Here, the seats after the seat change will be displayed.\n" +
                            "You can also move your seat from this screen. You can return to the main screen to customize the members and seats.",
                    R.drawable.lottery_screen
                )
            )
        )
    }
}

/**
 * 使い方カードをHorizontal pagerによって表示
 * @param cardsList 使い方カードの情報が入ったリスト
 */
@ExperimentalPagerApi
@Composable
fun UsageCardView(cardsList: List<UsageData>, contentPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        val pagerState = rememberPagerState()

        // カードの表示
        HorizontalPager(
            count = 5,
            state = pagerState,
            itemSpacing = 20.dp,
            // 水平パディングをページの中央に追加
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) { page ->
            UsageCard(
                cardsList[page].name,
                cardsList[page].description,
                cardsList[page].id
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        // TODO : ActionsRowを使えるようにする
//        ActionsRow(
//            pagerState = pagerState,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
    }
}

/**
 * 使い方のカード
 * @param cardName タイトル
 * @param description 説明文
 * @param imgId 画像の保存id
 */
@Composable
fun UsageCard(
    cardName: String,
    description: String,
    imgId: Int
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 5.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                text = cardName,
                fontFamily = fontsBold,
                fontSize = 30.sp
            )
            SubText(
                text = description,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    bottom = 20.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            )
            Image(
                painter = painterResource(id = imgId),
                contentDescription = "usage card $cardName",
                modifier = Modifier
                    .padding(
                        start = 32.dp,
                        end = 32.dp,
                        bottom = 16.dp
                    )
                    .clip(shape = RoundedCornerShape(1.dp))
                    .shadow(shape = CircleShape, elevation = 0.dp)
                    .size(width = 266.dp, height = 570.dp)
                    .border(BorderStroke(5.dp, MaterialTheme.colors.onPrimary))
            )
        }

    }
}

/**
 * アプリのトップバー
 * @param onNavigationClick 戻るボタンが押された時の処理
 */
@Composable
fun UsageTopBar(onNavigationClick: () -> Unit) {
    com.google.accompanist.insets.ui.TopAppBar(
        title = {
            Text(
                text = "How to use Seat Assist",
                fontFamily = fontsBold
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationClick() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back button")
            }
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
        contentPadding = rememberInsetsPaddingValues(
            LocalWindowInsets.current.statusBars,
            applyBottom = false,
        )
    )
}