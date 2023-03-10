package com.example.news_v2.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.news_v2.ui.home.ArticleView
import com.example.news_v2.ui.home.HomeViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchPage(homeViewModel: HomeViewModel, parentNavController: NavHostController){

    var searchQuery by remember{
        mutableStateOf("")
    }

    var isSearchCliked by remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current
//    val searchResult by homeViewModel.searchResult.observeAsState()
//    Log.d(TAG,"searchResult : ${searchResult.toString()}")
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        // search view

        TextField(
            value = searchQuery,
            onValueChange = { value ->
                isSearchCliked = false
                searchQuery = value
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
            label = {
                    Text(text = "Search here")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (searchQuery.isNotBlank()) {
                    IconButton(
                        onClick = {
                            searchQuery = ""
                            isSearchCliked = false
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                isSearchCliked = true
                keyboardController?.hide()
            })
        )


        if (isSearchCliked){
            val articleData = homeViewModel.searchNews(searchQuery).collectAsLazyPagingItems()

            LazyColumn(){
                items(articleData) { article ->
                    article?.let {
                        ArticleView(article = it) {
                            parentNavController.navigate("articleDetailPage/$it")
                        }
                    }
                }
                when (articleData.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    LoadState.Loading -> {
                        item {
                            CircularProgressBar(modifier = Modifier
                                .size(30.dp)
                                .align(alignment = Alignment.CenterHorizontally))
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            Text(text = "Failed to load Headlines"
                    ,modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchView(onSearchQueryChanged : (query : String) -> Unit){

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SearchPagePreview(){
    SearchPage(viewModel(), rememberNavController())
}