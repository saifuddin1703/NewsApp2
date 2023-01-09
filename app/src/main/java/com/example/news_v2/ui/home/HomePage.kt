package com.example.news_v2.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import com.example.news_v2.data.models.Article
import com.example.news_v2.ui.CircularProgressBar
import com.example.news_v2.ui.theme.Pink80
import com.example.news_v2.ui.theme.Typography
import com.example.news_v2.utils.DateUtils
import com.example.news_v2.utils.TAG
import com.example.news_v2.utils.categories

//import androidx.compose.runtime.livedata.observeAsState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(homeViewModel: HomeViewModel, parentNavController: NavHostController){

    var selectedCategory by remember{
        mutableStateOf("Business")
    }

    val headlinesData = homeViewModel.fetchHeadlinesOfCategory(selectedCategory).collectAsLazyPagingItems()
//    Log.d(TAG,"test")

    Column(modifier = Modifier
        .background(color = Color.White))
    {

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)) {

            Text(text = "News",
                fontSize = Typography.headlineSmall.fontSize
                ,modifier = Modifier
                    .padding(start = 15.dp)
                    .align(alignment = Alignment.CenterVertically))
        }

        Text(text = "Top Headlines",
        fontSize = Typography.headlineMedium.fontSize,
        fontWeight = Typography.headlineMedium.fontWeight,
        modifier = Modifier.padding(start = 10.dp,end = 10.dp,top = 10.dp,bottom = 10.dp))

        CategoryList(onSelect = {
            selectedCategory = it
        })

        LazyColumn(){
            itemsIndexed(headlinesData) { index,article ->
                article?.let {
                    ArticleView(article = it, onClick = {
                        parentNavController.navigate("articleDetailPage/${article}")
                    })
                }
            }
//            when (headlinesData.loadState.append) {
//                is LoadState.NotLoading -> Unit
//                LoadState.Loading -> {
//                    item {
//                        CircularProgressBar(modifier = Modifier
//                            .size(30.dp)
//                            .align(alignment = CenterHorizontally))
//                    }
//                }
//                is LoadState.Error -> {
//                    item {
//                        Text(text = "Failed to load Headlines"
//                            ,modifier = Modifier.align(alignment = CenterHorizontally))
//                    }
//                }
//            }
        }

    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleView(article: Article,onClick : () -> Unit){

    Row(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
        .shadow(elevation = 10.dp)
        .fillMaxWidth()
        .height(100.dp)
        .background(color = Color.White)
        .clickable {
            onClick()
        }

    )
    {
        AsyncImage(model = article.urlToImage
            , contentDescription = "Article Image"
            ,modifier = Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight()
                .background(color = Color.Red)
        , contentScale = ContentScale.Crop)

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            ) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                ){

                Box(modifier = Modifier
                    .padding(start = 10.dp)
                    .wrapContentSize()
                    .align(alignment = Alignment.CenterStart)
                    .background(color = Pink80, shape = RoundedCornerShape(5.dp)))
                {
                    article.source?.name?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            modifier = Modifier
                                .padding(5.dp)
                                .background(color = Pink80),
                            fontSize = 12.sp
                        )
                    }
                }

                article.publishedAt?.let {
                    Text(text = DateUtils.getDate(it),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .align(alignment = Alignment.BottomEnd),
                    fontSize = 15.sp)
                }
            }

            // title
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ) {
                Text(
                    text = article.title.toString(),
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .align(alignment = Alignment.Center)
                    ,maxLines = 2
                )
            }
        }
    }
}

@Composable
fun CategoryList(onSelect : (selectedCategory : String) -> Unit){
//    var select
    var selectedIndex by remember{
        mutableStateOf(0)
    }
    LazyRow(
        modifier = Modifier.background(color = Color.White)
    ){
        itemsIndexed(categories){ index,category ->
            Box(modifier = Modifier
                .padding(5.dp)
                .clickable {
                    selectedIndex = index
                    onSelect(category)
                }
                .wrapContentSize()
                .background(
                    color = if (index == selectedIndex) Color.Black else Color.Gray,
                    shape = RoundedCornerShape(10.dp)
                ))
            {
                Text(text = category,
                modifier = Modifier
                    .padding(10.dp)
                , color = if (index == selectedIndex) Color.White else Color.White)
            }
        }
    }
}

@Composable
@Preview
fun CategoriesPreview(){
    CategoryList(onSelect = {

    })
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun ArticlePreview(){
    ArticleView(article = Article(), onClick = {

    })
}

@Composable
@Preview
fun HomePagePreview(){

//    HomePage(homeViewModel = )
}