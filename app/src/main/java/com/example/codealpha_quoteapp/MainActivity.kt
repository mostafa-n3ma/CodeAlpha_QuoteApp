package com.example.codealpha_quoteapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.codealpha_quoteapp.operations.Repository
import com.example.codealpha_quoteapp.operations.dataEntities.CacheQuoteItem
import com.example.codealpha_quoteapp.presentation.QuoteViewModel
import com.example.codealpha_quoteapp.presentation.ViewModelEvents
import com.example.codealpha_quoteapp.ui.theme.CodeAlpha_QuoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val vieWModel : QuoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeAlpha_QuoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestComposable(vieWModel = vieWModel)

                }
            }
        }
    }
}

@Composable
fun TestComposable(vieWModel:QuoteViewModel?=null) {
    val historyList: State<List<CacheQuoteItem>?> = vieWModel!!.historyQuotesList.observeAsState()
    val favList: State<List<CacheQuoteItem>?> = vieWModel.favoritesQuotesList.observeAsState()
    Log.d("MainActivity", "TestComposable:history list size : ${historyList.value?.size} \n favorites list size ${favList.value?.size} ")



    val currentRandomQuote: State<CacheQuoteItem?> = vieWModel!!.currentRandomQuote.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { vieWModel.setEvent(ViewModelEvents.UpdateCurrenRandomQuote) }) {
            Text(text = "Random Quote!")
        }

        Text(text = "${currentRandomQuote.value?.content}")

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodeAlpha_QuoteAppTheme {
        TestComposable()
    }
}