package com.mio.lab3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ViewIconState {
    var label: String by mutableStateOf("");
    var iconId: Int by mutableStateOf(0);
}


class ModelState : ViewModel(){
    var viewIcon by mutableStateOf(ViewIconState())
    var fontSize:Int by mutableStateOf(16)
    var fontColor:Color by mutableStateOf(Color.Black)
    fun setViewIcon(label: String, iconId: Int){
        viewIcon.label = label
        viewIcon.iconId = iconId
        }
}