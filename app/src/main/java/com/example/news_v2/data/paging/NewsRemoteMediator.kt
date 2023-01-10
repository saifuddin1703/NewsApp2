package com.example.news_v2.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.news_v2.data.models.Article
import com.example.news_v2.data.models.RemoteKeys
import com.example.news_v2.retrofit.services.NewsAPI
import com.example.news_v2.room.NewsDatabase
import com.example.news_v2.utils.NumberUtils
import com.example.news_v2.utils.TAG
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val category : String ,
    private val newsAPI: NewsAPI,
    private val newsDatabase: NewsDatabase
) : RemoteMediator<Int,Article>(){

    private val NEWS_STARTING_PAGE_INDEX = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)

                remoteKeys?.nextKey?.minus(1) ?: NEWS_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)

                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)

                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                nextKey
            }
        }

        try {

            val apiResponse = newsAPI.getHeadlinesOfCategory(category = category, page = page, pageSize = 10)

            val articles = apiResponse.articles.map {
                it.id = NumberUtils.generateRandomId()
                it
            }

            Log.d(TAG,"article size = ${articles.size}")
            val endReached = articles.isEmpty()

            newsDatabase.withTransaction {
                val newsDao = newsDatabase.getNewsDao()
                val remoteKeysDao = newsDatabase.getRemoteKeysDao()

                if (loadType == LoadType.REFRESH){
                    remoteKeysDao.clearRemoteKeys()
                    newsDao.clearAll()
                }

                val prevKey = if(page == NEWS_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endReached) null else page + 1

                val keys = articles.map{
                    RemoteKeys(articleUrl = it.url, nextKey = nextKey, prevKey = prevKey)
                }

                remoteKeysDao.insertAll(remoteKey = keys)
//                Log.d(TAG,articles.size.toString())
                newsDao.insertAllArticles(articles)
//                Log.d(TAG,newsDao.get().size.toString())
//                Log.d(TAG,newsDao.get().size.toString())
//                news
            }

            return MediatorResult.Success(endOfPaginationReached = endReached)
        }catch (e : IOException){
            return MediatorResult.Error(e)
        }catch (e : HttpException){
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Article>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { article ->
                // Get the remote keys of the last item retrieved
                newsDatabase.getRemoteKeysDao().remoteKeysArticleUrl(article.url)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Article>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { article ->
                // Get the remote keys of the first items retrieved
                newsDatabase.getRemoteKeysDao().remoteKeysArticleUrl(article.url)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { articleUrl ->
                newsDatabase.getRemoteKeysDao().remoteKeysArticleUrl(articleUrl = articleUrl)
            }
        }
    }

}