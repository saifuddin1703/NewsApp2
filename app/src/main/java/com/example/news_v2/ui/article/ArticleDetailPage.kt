package com.example.news_v2.ui.article

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news_v2.R
import com.example.news_v2.data.models.Article
import com.example.news_v2.ui.theme.Typography
import com.example.news_v2.utils.dummy_news_description
import com.example.news_v2.utils.dummy_news_link
import com.example.news_v2.utils.dummy_news_title

@Composable
fun ArticleDetailPage(article: Article) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.dummy_image),
            contentDescription = "dummy",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .align(alignment = Alignment.TopCenter),
        contentScale = ContentScale.Crop)

        Column(modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .align(alignment = Alignment.BottomCenter)
            .verticalScroll(state = rememberScrollState())
        ) {

            article.title?.let {
                Text(text = it, fontSize = Typography.bodyLarge.fontSize, fontWeight =  FontWeight.Bold, modifier = Modifier
                    .padding(start = 10.dp,end = 10.dp, top = 20.dp)
                )
            }

            article.content?.let {
                Text(text = it, fontSize = Typography.bodyLarge.fontSize, modifier = Modifier
                    .padding(start = 10.dp,end = 10.dp, top = 20.dp, bottom = 10.dp)
                )
            }

            Text(text = "Read full article here : "
            , fontSize = Typography.bodyLarge.fontSize
            , fontWeight = FontWeight.SemiBold
            , modifier = Modifier
                    .padding(start = 10.dp,end = 10.dp, bottom = 10.dp)
            )

            Text(text = article.url
                , fontSize = Typography.bodyLarge.fontSize
                , fontWeight = FontWeight.SemiBold
                , color = Color.Blue
                , textDecoration = TextDecoration.Underline
            , modifier = Modifier
                    .padding(start = 10.dp,end = 10.dp, bottom = 20.dp)
                    .clickable {

                    }
            )
        }
    }
}

@Composable
@Preview
fun ArticleDetailPagePreview(){
    ArticleDetailPage(article = Article(
        title = dummy_news_title,
        content = dummy_news_description,
        url = dummy_news_link
    )
    )
}