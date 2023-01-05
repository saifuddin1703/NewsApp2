package com.example.news_v2.ui.paging

import android.app.ApplicationErrorReport
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.news_v2.data.models.Article
import com.example.news_v2.repositories.NewsRepository

class HeadLinesPagingDataSource(
    private val repository: NewsRepository,
    private val category: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getTopHeadlinesOfCategory(category,nextPageNumber,10)

            LoadResult.Page(
                data = response.articles,
                prevKey = null,
                nextKey = if (response.articles.isEmpty()) null else nextPageNumber + 1
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}