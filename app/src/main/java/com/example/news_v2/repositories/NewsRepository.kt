package com.example.news_v2.repositories

import com.example.news_v2.retrofit.response.NewsResponse
import com.example.news_v2.services.NewsService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class NewsRepository(
    private val newsService: NewsService
) {
    suspend fun getEverything() = newsService.getEverything()

    suspend fun getTopHeadlines() = newsService.getTopHeadlines()

    suspend fun searchNews(searchQuery : String) = newsService.searchNews(searchQuery = searchQuery)

    suspend fun getNewsOfCategory(category : String) = newsService.getNewsOfCategory(category = category)
}