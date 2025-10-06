package com.mio.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mio.myapplication.R



@Composable
fun SpaceTravelScreen(

    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    // 定义颜色以便复用
    val darkGreen = Color(0xFF1E6A53)
    val lightOrange = Color(0xFFD98E35)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color(0xFFF0F0F0)) // 给个浅灰色背景
    ) {
        // 顶部导航栏
        TopNavigationBar()

        Spacer(modifier = Modifier.height(32.dp))

        // 出发地和目的地
        FromToSection(darkGreen)

        Spacer(modifier = Modifier.height(24.dp))

        // 单程和乘客数量
        TripOptionsSection(lightOrange)

        // 中间的动画/图片区域，使用 weight 占据剩余空间
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            // 背景的星系图
            Image(
                painter = painterResource(id = R.drawable.galaxy_icon), // 请替换成你的图片资源
                contentDescription = "Galaxy",
                modifier = Modifier.size(200.dp)
            )
            // 左边的小火箭
            Image(
                painter = painterResource(id = R.drawable.rocket_icon), // 请替换成你的火箭图片
                contentDescription = "Rocket",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 50.dp)
            )
        }

        // 底部出发按钮
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = darkGreen),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "DEPART",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun TopNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly, // 让子项均匀分布
        verticalAlignment = Alignment.CenterVertically
    ) {
        headerData.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = item.iconId),
                    contentDescription = item.symbol
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.symbol,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun FromToSection(backgroundColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 出发地
        Text(
            text = "DCA",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(8.dp))
                .padding(horizontal = 40.dp, vertical = 12.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // 交换图标
        Box(
           modifier = Modifier
               .background(Color.White)
               .clip(RoundedCornerShape(8.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.double_arrows),

                contentDescription = "Swap",
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // 目的地
        Text(
            text = "MARS",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(8.dp))
                .padding(horizontal = 40.dp, vertical = 12.dp)
        )
    }
}

@Composable
fun TripOptionsSection(backgroundColor: Color) {
    var isOneWay by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.Start // 左对齐
    ) {
        // 单程选项
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp)) // 圆角
                .background(backgroundColor)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("One Way", color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isOneWay,
                onCheckedChange = { isOneWay = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.White.copy(alpha = 0.5f),
                    uncheckedThumbColor = Color.Gray,
                    uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f),
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 乘客数量
        Text(
            text = "1 Traveller",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
    }
}

// 添加一个预览函数，方便在Android Studio中查看效果
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // 假设你的应用有一个主题，这里用一个空的 Composable 包裹
    Column {
        SpaceTravelScreen()
    }
}