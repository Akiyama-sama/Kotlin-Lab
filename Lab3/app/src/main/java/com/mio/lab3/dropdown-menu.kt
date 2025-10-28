package com.mio.lab3

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext // 引入 LocalContext
import androidx.compose.ui.unit.dp

data class FontOption(val label: String, val fontSize: Int, val color: Color)
data class ColorOption(val label: String, val color: Color) // 新增颜色数据类

@Composable
fun MinimalDropdownMenu(
    modifier: Modifier = Modifier, // 添加默认值，方便使用
    state: ModelState
) {
    val context = LocalContext.current // 获取 Context 用于 Toast

    // --- 菜单状态 ---
    var expanded by remember { mutableStateOf(false) } // 主菜单
    var fontSizeExpanded by remember { mutableStateOf(false) } // 字体大小子菜单
    var colorExpanded by remember { mutableStateOf(false) } // 字体颜色子菜单 (新增)

    // --- 状态数据 ---
    val currentFontSize = state.fontSize
    val currentFontColor = state.fontColor

    // --- 菜单数据 ---
    val fontOptions = listOf(
        FontOption("小 (10pt)", 10, Color.Black),
        FontOption("中 (16pt)", 16, Color.Black),
        FontOption("大 (20pt)", 20, Color.Black)
    )

    val colorOptions = listOf( // 字体颜色选项
        ColorOption("红色", Color.Red),
        ColorOption("黑色", Color.Black)
    )

    // --- 修正：移除 AlertDialog ！！！ ---
    // AlertDialog 不能直接放在 DropdownMenu 内。
    // 如果需要显示对话框，它必须由一个状态控制，并在 Box 或 Column 外部被调用。

    Box(
        modifier = modifier.padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "更多选项")
        }

        // --- 主菜单 ---
        DropdownMenu(
            expanded = expanded,
            // 任何菜单关闭时，确保所有子菜单状态都被重置
            onDismissRequest = { expanded = false; fontSizeExpanded = false; colorExpanded = false }
        ) {

            // ===================================================
            // 1. 字体大小 (级联菜单)
            // ===================================================
            DropdownMenuItem(
                text = { Text("字体大小 (${currentFontSize}pt) >") },
                onClick = { fontSizeExpanded = !fontSizeExpanded }
            )
            // 字体大小子菜单
            DropdownMenu(
                expanded = fontSizeExpanded,
                onDismissRequest = { fontSizeExpanded = false }
            ) {
                fontOptions.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option.label,
                                color = if (option.fontSize == currentFontSize) Color.Red else Color.Black
                            )
                        },
                        onClick = {
                            state.fontSize = option.fontSize // 设置字体大小
                            expanded = false    // 关闭所有菜单
                            fontSizeExpanded = false
                        }
                    )
                }
            }

            // ===================================================
            // 2. 普通菜单项 (弹出 Toast)
            // ===================================================
            DropdownMenuItem(
                text = { Text("普通菜单项") },
                onClick = {
                    Toast.makeText(context, "已点击普通菜单项", Toast.LENGTH_SHORT).show()
                    expanded = false // 关闭主菜单
                }
            )

            // ===================================================
            // 3. 字体颜色 (级联菜单 - 新增)
            // ===================================================
            DropdownMenuItem(
                // 显示当前颜色名称
                text = { Text("字体颜色 (${if (currentFontColor == Color.Red) "红色" else "黑色"}) >") },
                onClick = { colorExpanded = !colorExpanded }
            )
            // 字体颜色子菜单
            DropdownMenu(
                expanded = colorExpanded,
                onDismissRequest = { colorExpanded = false }
            ) {
                colorOptions.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option.label,
                                // 高亮当前选中项
                                color = if (option.color == currentFontColor) Color.Red else Color.Black
                            )
                        },
                        onClick = {
                            state.fontColor = option.color // 设置字体颜色
                            expanded = false    // 关闭主菜单
                            colorExpanded = false
                        }
                    )
                }
            }
        }
    }
}