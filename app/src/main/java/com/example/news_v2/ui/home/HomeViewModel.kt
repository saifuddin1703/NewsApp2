package com.example.news_v2.ui.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news_v2.data.models.Article
import com.example.news_v2.repositories.NewsRepository
import com.example.news_v2.retrofit.APIClient
import com.example.news_v2.ui.paging.HeadLinesPagingDataSource
import com.example.news_v2.ui.paging.SearchPagingDataSource
import com.example.news_v2.utils.APIResult
import com.example.news_v2.utils.TAG
import kotlinx.coroutines.flow.Flow
import java.io.StringReader

class HomeViewModel() : ViewModel() {

    private var newsRepository: NewsRepository = NewsRepository(APIClient.getNewsService())

    private var _topHeadlinesResult : MutableLiveData<APIResult<List<Article>>> = MutableLiveData(APIResult.loading())

    private var _topHeadlinesOfCategoryResult : MutableLiveData<APIResult<List<Article>>> = MutableLiveData(APIResult.loading())
    var topHeadlinesOfCategoryResult : LiveData<APIResult<List<Article>>> = _topHeadlinesOfCategoryResult

//    private var _searchResult : MutableLiveData<APIResult<List<Article>>> = MutableLiveData(APIResult.loading())
//    var searchResult : MutableLiveData<APIResult<List<Article>>> = MutableLiveData(APIResult.loading())
//    var searchResult : Flow<PagingData<Article>>

//    fun fetchTopHeadlines() {
//        _topHeadlinesResult = liveData {
//            emit(APIResult.loading())
//            try {
//                val response = newsRepository.getTopHeadlines()
//                if (response.isSuccessful) {
//                    response.body()?.articles?.let {
//                        emit(APIResult.success(it))
//                    }
//                } else {
//                    throw IOException("Failed to connect to API")
//                }
//            } catch (e: IOException) {
//                emit(APIResult.error(e.message))
//                Log.d(TAG, "error fetching because ${e.message}")
//            }
//        }
//    }
//    fun searchNews(query : String) {
//        _searchResult = liveData {
//            emit(APIResult.loading())
//            try {
//                val response = newsRepository.searchNews(searchQuery = query)
//                if (response.isSuccessful) {
//                    response.body()?.articles?.let {
//                        emit(APIResult.success(data = it))
//                    }
//                } else {
//                    throw IOException("Failed to search ")
//                }
//            } catch (e: IOException) {
//                emit(APIResult.error(e.message))
//                Log.d(TAG, "error fetching because ${e.message}")
//            }
//        }
//    }

    fun searchNews(query : String): Flow<PagingData<Article>> {
        val searchResultPager = Pager(PagingConfig(5)){
            SearchPagingDataSource(query,newsRepository)
        }.flow.cachedIn(viewModelScope)

        return searchResultPager
    }

    fun fetchHeadlinesOfCategory(category : String): Flow<PagingData<Article>> {
       val headlinesResultPage = Pager(PagingConfig(pageSize = 10)){
           HeadLinesPagingDataSource(repository = newsRepository,category)
       }.flow.cachedIn(viewModelScope)

        return headlinesResultPage
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
////                        emit(APIResult.success(it))
//                        _topHeadlinesOfCategoryResult.postValue(APIResult.success(it))
//                    }
//                } else {
//                    throw IOException("Failed to connect to API")
//                }
//            } catch (e: IOException) {
////                emit(APIResult.error(e.message))
//                _topHeadlinesOfCategoryResult.postValue(APIResult.error(e.message))
//                Log.d(TAG, "error fetching because ${e.message}")
//            }
//        }


}