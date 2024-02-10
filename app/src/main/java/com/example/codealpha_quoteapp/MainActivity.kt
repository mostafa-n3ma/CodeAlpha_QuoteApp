package com.example.codealpha_quoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.codealpha_quoteapp.presentation.BottomNavigationItem
import com.example.codealpha_quoteapp.presentation.QuoteViewModel
import com.example.codealpha_quoteapp.ui.theme.CodeAlpha_QuoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

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
                    NavigationBarComposable(vieWModel)
                }
            }
        }
    }


}


@Composable
private fun NavigationBarComposable(vieWModel: QuoteViewModel) {
    val items = listOf(
        BottomNavigationItem(
            title = "Favorite",
            selectedIcon = painterResource(id = R.drawable.favourite_filled),
            unSelectedIcon = painterResource(id = R.drawable.favourite_outlined)
        ),

        BottomNavigationItem(
            title = "Random Quote",
            selectedIcon = painterResource(id = R.drawable.random_filled),
            unSelectedIcon = painterResource(id = R.drawable.random_outlined)
        ),
        BottomNavigationItem(
            title = "History",
            selectedIcon = painterResource(id = R.drawable.history_filled),
            unSelectedIcon = painterResource(id = R.drawable.history_outlined)
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
                NavigationBar {
                    items.forEachIndexed{index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = { selectedItemIndex = index },
                            alwaysShowLabel = true,
                            label = {
                                    Text(
                                        text = item.title,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily(Font(R.font.atma_bold))
                                        )
                                    )
                            },
                            icon = {
                                Icon(
                                    painter = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else {
                                        item.unSelectedIcon
                                    }, contentDescription = item.title
                                )
                            }
                        )
                    }
                }
        }
    ) {

    }












}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodeAlpha_QuoteAppTheme {
    }
}