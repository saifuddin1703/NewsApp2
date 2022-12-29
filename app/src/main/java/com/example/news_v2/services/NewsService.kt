package com.example.news_v2.services

import com.example.news_v2.retrofit.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/everything")
    suspend fun getEverything() : Call<NewsResponse>

    @GET("/top-headlines")
    suspend fun getTopHeadlines() : Call<NewsResponse>

    @GET("/everything")
    suspend fun searchNews(@Query("q") searchQuery : String) : Call<NewsResponse>

    @GET("/everything")
    suspend fun getNewsOfCategory(@Query("category") category : String) : Call<NewsResponse>

}