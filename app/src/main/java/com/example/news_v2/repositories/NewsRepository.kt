package com.example.news_v2.repositories

import android.util.Log
import androidx.paging.*
import com.example.news_v2.data.models.Article
import com.example.news_v2.data.paging.NewsRemoteMediator
import com.example.news_v2.retrofit.services.NewsAPI
import com.example.news_v2.room.NewsDatabase
import com.example.news_v2.utils.TAG
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsAPI,
    private val newsDataBase : NewsDatabase
) {
    suspend fun getEverything() = newsService.getEverything()

    suspend fun getTopHeadlines() = newsService.getTopHeadlines()

    suspend fun searchNews(searchQuery : String,pageNumber: Int,pageSize : Int) = newsService.searchNews(searchQuery = searchQuery, page = pageNumber,pageSize = pageSize)

    @OptIn(ExperimentalPagingApi::class)
    fun getTopHeadlinesOfCategory(category : String): Flow<PagingData<Article>> {

        val pagingSourceFactory = {
            newsDataBase.getNewsDao().getAllNews()
//            PagingSource.
        }

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = NewsRemoteMediator(
                newsAPI = newsService,
                newsDatabase = newsDataBase,
                category = category
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


}