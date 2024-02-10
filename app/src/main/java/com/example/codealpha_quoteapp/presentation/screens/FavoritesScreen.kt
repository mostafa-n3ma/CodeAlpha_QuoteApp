package com.example.codealpha_quoteapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.codealpha_quoteapp.presentation.QuoteViewModel

@Composable
fun FavoritesScreen(vieWModel: QuoteViewModel?=null) {
    val favoritesList = vieWModel!!.favoritesQuotesList.observeAsState()
    LazyQuoteColumn(listState = favoritesList)
}




@Preview
@Composable
fun FavScreenPreview() {
    FavoritesScreen()
}