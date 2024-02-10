package com.example.codealpha_quoteapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codealpha_quoteapp.R
import com.example.codealpha_quoteapp.operations.dataEntities.CacheQuoteItem
import com.example.codealpha_quoteapp.presentation.QuoteViewModel
import com.example.codealpha_quoteapp.presentation.ViewModelEvents

@Composable
fun MainScreen(vieWModel: QuoteViewModel? = null) {
    val currentRandomQuote: State<CacheQuoteItem?> = vieWModel!!.currentRandomQuote.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 100.dp)
                .rotate(-4f)
                .background(Color.Black)
                .align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10))
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(10))
                    .padding(16.dp)
                    .align(Alignment.Center)

            ) {
            }
        }

        QuoteCard(
            content = currentRandomQuote.value?.content?:"",
            author = currentRandomQuote.value?.author?:"",
            length = currentRandomQuote.value?.length?:0,
            isFavorite = currentRandomQuote.value?.favorite?:false,
            modifier = Modifier
                .padding(top = 100.dp)
                .width(300.dp)
                .height(400.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10))
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(10))
                .padding(16.dp)
                .align(Alignment.TopCenter)
        )

        OutlinedButton(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 100.dp),
            onClick = { vieWModel.setEvent(ViewModelEvents.UpdateCurrentRandomQuote) },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(10),
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.generate), contentDescription ="" )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Random Quote"
            )
        }


    }

}

@Composable
fun QuoteCard(
    content: String,
    author: String,
    length: Int,
    isFavorite: Boolean,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart),
            painter = painterResource(id = R.drawable.quote),
            contentDescription = "")
        quoteContentText(
            content = content,
            length = length,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 8.dp)
        )


        AuthorText(author,
            Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp, bottom = 35.dp))

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)

        ) {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.favourite_outlined),
                    contentDescription = ""
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.share), contentDescription = "")
            }
        }



    }
}

@Composable
fun AuthorText(author: String, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = author,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.atma_regular))
        )
    )
}

@Composable
fun quoteContentText(content: String, length: Int, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = content,
        style = TextStyle(
            fontSize = if (length < 100) 24.sp else if(length>150) 14.sp else 18.sp,
            fontFamily = FontFamily(Font(R.font.atma_bold))
        )
    )
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}