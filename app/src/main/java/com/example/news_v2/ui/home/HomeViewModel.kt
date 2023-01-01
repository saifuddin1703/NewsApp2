package com.example.news_v2.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.news_v2.data.models.Article
import com.example.news_v2.repositories.NewsRepository
import com.example.news_v2.retrofit.APIClient
import com.example.news_v2.retrofit.response.NewsResponse
import com.example.news_v2.utils.TAG
import com.example.news_v2.utils.Result
import com.example.news_v2.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.io.IOException

class HomeViewModel() : ViewModel() {

    private var newsRepository: NewsRepository = NewsRepository(APIClient.getNewsService())

    fun getTopHeadlines(): LiveData<Result<List<Article>>> = liveData {
        emit(Result.loading())
        try {
            val response = newsRepository.getTopHeadlines()
            if (response.isSuccessful){
                response.body()?.articles?.let {
                    emit(Result.success(it))
                }
            }else{
                throw IOException("Failed to connect to API")
            }
        }catch (e : IOException){
            emit(Result.error(e.message))
            Log.d(TAG,"error fetching because ${e.message}")
        }
    }

    fun searchNews(query : String) : LiveData<Result<List<Article>>> = liveData {
        emit(Result.loading())
        try {
            val response = newsRepository.searchNews(searchQuery = query)
            if(response.isSuccessful){
                response.body()?.articles?.let {
                    emit(Result.success(data = it))
                }
            }else{
                throw IOException("Failed to search ")
            }
        }catch (e : IOException){
            emit(Result.error(e.message))
            Log.d(TAG,"error fetching because ${e.message}")
        }
    }

}