package com.example.news_v2.ui.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import com.example.news_v2.data.models.Article
import com.example.news_v2.data.paging.NewsRemoteMediator
import com.example.news_v2.repositories.NewsRepository
import com.example.news_v2.data.paging.SearchPagingDataSource
import com.example.news_v2.retrofit.services.NewsAPI
import com.example.news_v2.room.NewsDatabase
import com.example.news_v2.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject() constructor() : ViewModel() {

    @Inject lateinit var newsRepository: NewsRepository
    @Inject lateinit var newsDataBase : NewsDatabase
    @Inject lateinit var newsService : NewsAPI

    fun searchNews(query : String): Flow<PagingData<Article>> {
        val searchResultPager = Pager(PagingConfig(5)){
            SearchPagingDataSource(query,newsRepository)
        }.flow.cachedIn(viewModelScope)

        return searchResultPager
    }

    @OptIn(ExperimentalPagingApi::class)
    fun fetchHeadlinesOfCategory(category : String): Flow<PagingData<Article>> {
        val pagingSourceFactory = {
            newsDataBase.getNewsDao().getAllNews()
//            Log.d(TAG,news.)
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
        ).flow.cachedIn(viewModelScope)
    }

}