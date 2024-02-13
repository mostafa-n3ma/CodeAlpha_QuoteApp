package com.example.codealpha_quoteapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
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

                    viewModel.shareQuote.observe(this, Observer {
                        Log.d("MainActivity", "onCreate:  quoteTxt : $it")
                        if (it.isNotEmpty()){
                            Log.d("MainActivity", "onCreate: quoteTxt is notEmpty share ->->>>> : $it")
                            shareQuote(it)
                        }
                    })


                }
            }
        }
    }


    fun shareQuote(quote:String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, quote)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }


}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodeAlpha_QuoteAppTheme {
    }
}