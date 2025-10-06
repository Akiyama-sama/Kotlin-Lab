package com.mio.myapplication

import com.mio.myapplication.R

data class HeaderData(
    val symbol: String,
    val iconId: Int
)

val headerData: List<HeaderData> = listOf(
    HeaderData("Space Stations", R.drawable.space_station_icon),
    HeaderData("Flights", R.drawable.rocket_icon),
    HeaderData("Rovers", R.drawable.rover_icon),
)