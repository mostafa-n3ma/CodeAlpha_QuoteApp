package com.example.codealpha_quoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.codealpha_quoteapp.presentation.QuoteViewModel
import com.example.codealpha_quoteapp.presentation.navigtion.NavigationBarComposable
import com.example.codealpha_quoteapp.ui.theme.CodeAlpha_QuoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel : QuoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeAlpha_QuoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                   AppNavigator(viewModel = viewModel)
                    NavigationBarComposable(vieWModel = viewModel)
                }
            }
        }
    }


}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodeAlpha_QuoteAppTheme {
    }
}