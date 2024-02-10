package com.example.codealpha_quoteapp.presentation

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem(
    val title:String,
    val selectedIcon:Painter,
    val unSelectedIcon:Painter
)

