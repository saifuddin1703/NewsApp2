package com.example.news_v2.room.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news_v2.data.models.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticles(articles : List<Article>)

    @Query("Delete from article")
    suspend fun clearAll()

    @Query("Select * from article")
    fun getAllNews() : PagingSource<Int,Article>
}