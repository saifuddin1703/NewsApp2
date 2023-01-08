package com.example.news_v2.retrofit.services

import com.example.news_v2.retrofit.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v2/everything")
    suspend fun getEverything() : NewsResponse

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(@Query("country") country : String = "in") : NewsResponse

    @GET("/v2/everything")
    suspend fun searchNews(@Query("q") searchQuery : String,@Query("page") page : Int,@Query("pageSize") pageSize : Int) : NewsResponse

    @GET("/v2/top-headlines")
    suspend fun getHeadlinesOfCategory(@Query("category") category : String,@Query("country") country: String = "in",@Query("page") page : Int,@Query("pageSize") pageSize : Int) : NewsResponse
}