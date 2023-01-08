package com.example.news_v2.ui.home

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news_v2.data.models.Article
import com.example.news_v2.repositories.NewsRepository
import com.example.news_v2.data.paging.SearchPagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject() constructor() : ViewModel() {

    @Inject lateinit var newsRepository: NewsRepository
//    private var _topHeadlinesResult : MutableLiveData<APIResult<List<Article>>> = MutableLiveData(APIResult.loading())
//
//    private var _topHeadlinesOfCategoryResult : MutableLiveData<APIResult<List<Article>>> = MutableLiveData(APIResult.loading())
//    var topHeadlinesOfCategoryResult : LiveData<APIResult<List<Article>>> = _topHeadlinesOfCategoryResult

    fun searchNews(query : String): Flow<PagingData<Article>> {
        val searchResultPager = Pager(PagingConfig(5)){
            SearchPagingDataSource(query,newsRepository)
        }.flow.cachedIn(viewModelScope)

        return searchResultPager
    }

    fun fetchHeadlinesOfCategory(category : String): Flow<PagingData<Article>> {
        return newsRepository.getTopHeadlinesOfCategory(category = category).cachedIn(viewModelScope)
    }

}