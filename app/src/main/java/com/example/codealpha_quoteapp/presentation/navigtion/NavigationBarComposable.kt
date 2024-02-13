package com.example.codealpha_quoteapp.presentation.navigtion

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codealpha_quoteapp.R
import com.example.codealpha_quoteapp.presentation.BottomNavigationItem
import com.example.codealpha_quoteapp.presentation.QuoteViewModel
import com.example.codealpha_quoteapp.presentation.screens.FavoritesScreen
import com.example.codealpha_quoteapp.presentation.screens.HistoryScreen
import com.example.codealpha_quoteapp.presentation.screens.MainScreen


@Composable
 fun NavigationBarComposable(
    vieWModel: QuoteViewModel
) {
    val navController = rememberNavController()

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
        mutableIntStateOf(1)
    }

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            NavigationBar {
                items.forEachIndexed{index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            val currentRout = navController.currentDestination?.route
                            when(item.title){
                                "Favorite" -> navController.navigate(AppDestinations.FavoritesScreen.rout)
                                "Random Quote" -> {
                                    if (currentRout == AppDestinations.MainScreen.rout){

                                    }else{
                                        navController.popBackStack()
                                    }
                                }
                                "History" -> navController.navigate(AppDestinations.HistoryScreen.rout)
                            }
                        },
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
//        AppNavigator(viewModel = vieWModel)
        ScreensNavHost(navController,vieWModel)
    }


}

@Composable
private fun ScreensNavHost(navController: NavHostController, vieWModel: QuoteViewModel) {
    NavHost(navController = navController, startDestination = AppDestinations.MainScreen.rout) {
        composable(
            AppDestinations.MainScreen.rout,
            popEnterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            MainScreen(vieWModel)
        }
        composable(

            AppDestinations.FavoritesScreen.rout,
            enterTransition = { slideInHorizontally { -it } },
            popExitTransition = { slideOutHorizontally { -it } }
        ) {
            FavoritesScreen(vieWModel)
        }
        composable(
            AppDestinations.HistoryScreen.rout,
            enterTransition = { slideInHorizontally { it} },
            popExitTransition = { slideOutHorizontally { it } }
        ) {
            HistoryScreen(vieWModel)
        }
    }
}