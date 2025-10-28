package com.mio.lab3

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mio.lab3.ui.theme.Pink40
import com.mio.lab3.ui.theme.Pink80

data class ViewIcon(val label:String, val iconId: Int)


val list = listOf(
    ViewIcon(label = "lion", iconId = R.drawable.lion),
    ViewIcon(label = "cat", iconId = R.drawable.cat),
    ViewIcon(label = "dog", iconId = R.drawable.dog),
    ViewIcon(label = "elephant", iconId = R.drawable.elephant),
    ViewIcon(label = "monkey", iconId = R.drawable.monkey),
    ViewIcon(label = "tiger", iconId = R.drawable.tiger)
)
@Composable
fun  ViewList(
    state: ModelState
){
    val item =state.viewIcon
    val fontColor = state.fontColor
    val setItem = state::setViewIcon


    Column (modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(100.dp).background(Pink80),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        )  {
            Text(text = "Compose dropdown")
            MinimalDropdownMenu(
                modifier = Modifier.fillMaxWidth()

                ,

                state=state
            )
        }
        Divider(color = Color.LightGray, thickness = 3.dp)
        list.forEach { icon ->
            val isSelected = icon.label == item.label
            // 选中状态的背景色：使用深红色 (0xFF8B0000)
            val backgroundColor = if (isSelected) Color(0xFF8B0000) else Color.White
            // 选中状态的文字颜色
            val textColor = if (isSelected) Color.White else Color.Black

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor) // 设置背景色
                    .clickable(onClick = {
                        setItem(icon.label,icon.iconId) // 更新选中状态
                    })
                    // 添加水平和垂直内边距
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                // 关键：将子元素推向两端 (Text:左, Image:右)
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically // 垂直居中
            ){

                Text(
                    text = icon.label,
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium
                )
                Image(
                    painter = painterResource(id = icon.iconId),
                    contentDescription = icon.label,

                    modifier = Modifier.size(50.dp)
                )
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp), // 外部边距
            contentAlignment = Alignment.Center // Box 内部内容居中
        ) {
            // 使用 Surface 来轻松实现圆角背景
            Surface(
                shape = RoundedCornerShape(20.dp), // 设置圆角半径
                color = Color.DarkGray, // 深灰色背景
                // 稍微抬高，模拟按钮的阴影效果
                shadowElevation = 4.dp
            ) {
                Text(
                    text = item.label,
                    color = Color.White, // 白色文字
                    // 内部填充，让按钮看起来更大
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.titleLarge // 较大的字体
                )
            }
        }
        Row (
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text="测试字体大小",
                fontSize = state.fontSize.sp,
                color =state.fontColor
            )
        }

    }

}