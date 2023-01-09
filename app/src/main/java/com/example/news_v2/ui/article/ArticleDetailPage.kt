package com.example.news_v2.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news_v2.R

@Composable
fun ArticleDetailPage(){
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.dummy_image),
            contentDescription = "dummy",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .align(alignment = Alignment.TopCenter),
        contentScale = ContentScale.Crop)

        Column(modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .align(alignment = Alignment.BottomCenter)) {

        }
    }
}

@Composable
@Preview
fun ArticleDetailPagePreview(){
    ArticleDetailPage()
}