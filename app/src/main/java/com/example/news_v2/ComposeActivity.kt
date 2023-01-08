package com.example.news_v2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.news_v2.ui.SearchPage
import com.example.news_v2.ui.home.HomePage
import com.example.news_v2.ui.home.HomeViewModel
import com.example.news_v2.ui.theme.News20Theme
import com.example.news_v2.ui.theme.Typography
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.Typography

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            News20Theme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.White,
                        darkIcons = true
                    )
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LandingPage()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage() {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            var selectedIndex by remember{
                mutableStateOf(0)
            }
            NavigationBar(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
                containerColor = Color.White
            ) {
                NavigationBarItem(selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate("home")
                    },
                    icon = {
                        Image(painter = painterResource(id = R.drawable.home_selected)
                            ,modifier = Modifier.size(30.dp),
                            contentDescription = "home Icon")
                    },
                    label = {
                        Text(text = "Home")
                    },
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate("search")
                    },
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.noun_search_5392104),
                            modifier = Modifier.size(30.dp),
                            contentDescription = "home Icon"
                        )
                    },
                    label = {
                        Text(text = "Search")
                    },
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                    },
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.noun_profile_802759),
                            modifier = Modifier.size(30.dp),
                            contentDescription = "home Icon"
                        )
                    },
                    label = {
                        Text(text = "Profile")
                    },
                )
            }
        }
        ) {
//
        NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(bottom = 80.dp)){
            composable("home"){

                HomePage(homeViewModel = hiltViewModel<HomeViewModel>())
            }
            composable("search"){
                SearchPage(homeViewModel = hiltViewModel<HomeViewModel>())
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    News20Theme {
//        Greeting("Android")
        LandingPage()
    }
}