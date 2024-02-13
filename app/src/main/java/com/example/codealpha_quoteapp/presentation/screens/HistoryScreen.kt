package com.example.codealpha_quoteapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.codealpha_quoteapp.operations.dataEntities.CacheQuoteItem
import com.example.codealpha_quoteapp.presentation.QuoteViewModel
import com.example.codealpha_quoteapp.presentation.ViewModelEvents


@Composable
fun HistoryScreen(vieWModel: QuoteViewModel? = null) {
    val historyList: State<List<CacheQuoteItem>?> = vieWModel!!.historyQuotesList.observeAsState()
    LazyQuoteColumn(historyList,vieWModel)
}

@Composable
fun LazyQuoteColumn(listState: State<List<CacheQuoteItem>?>, vieWModel: QuoteViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(listState.value?.asReversed()?: emptyList()) {
                QuoteCard(
                    vieWModel = vieWModel,
                    content = it.content ?: "",
                    author = it.author ?: "",
                    length = it.length ?: 0,
                    isFavorite = it.favorite,
                    onFavClicked = {
                        vieWModel.setEvent(ViewModelEvents.ChangeQuoteFavoriteStatusEvent(it))
                    },
                    modifier = Modifier
                        .width(400.dp)
                        .height(400.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10))
                        .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(10))
                        .padding(16.dp)
                        .align(Alignment.TopCenter)
                )
            }
        }
    }

}



