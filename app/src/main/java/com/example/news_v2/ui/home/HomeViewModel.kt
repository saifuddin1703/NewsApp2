package com.example.news_v2.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.news_v2.data.models.Article
import com.example.news_v2.repositories.NewsRepository
import com.example.news_v2.retrofit.APIClient
import com.example.news_v2.utils.Result
import com.example.news_v2.utils.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel() : ViewModel() {

    private var newsRepository: NewsRepository = NewsRepository(APIClient.getNewsService())

    private var _topHeadlinesResult : MutableLiveData<Result<List<Article>>> = MutableLiveData(Result.loading())

    private var _topHeadlinesOfCategoryResult : MutableLiveData<Result<List<Article>>> = MutableLiveData(Result.loading())
    var topHeadlinesOfCategoryResult : LiveData<Result<List<Article>>> = _topHeadlinesOfCategoryResult

    var _searchResult : LiveData<Result<List<Article>>> = MutableLiveData(Result.loading())

//    fun fetchTopHeadlines() {
//        _topHeadlinesResult = liveData {
//            emit(Result.loading())
//            try {
//                val response = newsRepository.getTopHeadlines()
//                if (response.isSuccessful) {
//                    response.body()?.articles?.let {
//                        emit(Result.success(it))
//                    }
//                } else {
//                    throw IOException("Failed to connect to API")
//                }
//            } catch (e: IOException) {
//                emit(Result.error(e.message))
//                Log.d(TAG, "error fetching because ${e.message}")
//            }
//        }
//    }
//    fun searchNews(query : String) {
//        _searchResult = liveData {
//            emit(Result.loading())
//            try {
//                val response = newsRepository.searchNews(searchQuery = query)
//                if (response.isSuccessful) {
//                    response.body()?.articles?.let {
//                        emit(Result.success(data = it))
//                    }
//                } else {
//                    throw IOException("Failed to search ")
//                }
//            } catch (e: IOException) {
//                emit(Result.error(e.message))
//                Log.d(TAG, "error fetching because ${e.message}")
//            }
//        }
//    }

    suspend fun fetchHeadlinesOfCategory(category : String) {
        _topHeadlinesOfCategoryResult.postValue(Result.loading())
        try{
            val response = newsRepository.getTopHeadlinesOfCategory(category)
            _topHeadlinesOfCategoryResult.postValue(Result.success(response.articles))
        }catch (e : Exception){
            Log.d(TAG,e.message.toString())
            _topHeadlinesOfCategoryResult.postValue(Result.error("Error"))
        }
    }

//
//        viewModelScope.launch(Dispatchers.IO) {
//            Log.d(TAG,"line 63")
//            try {
//                val response = newsRepository.getTopHeadlinesOfCategory(category)
//                Log.d(TAG,"line 66")
//                if (response.isSuccessful) {
//                    response.body()?.articles?.let {
//                        Log.d(TAG,it.toString())
////                        emit(Result.success(it))
//                        _topHeadlinesOfCategoryResult.postValue(Result.success(it))
//                    }
//                } else {
//                    throw IOException("Failed to connect to API")
//                }
//            } catch (e: IOException) {
////                emit(Result.error(e.message))
//                _topHeadlinesOfCategoryResult.postValue(Result.error(e.message))
//                Log.d(TAG, "error fetching because ${e.message}")
//            }
//        }


}