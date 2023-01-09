package com.example.news_v2.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news_v2.data.models.Article
import com.example.news_v2.data.models.SavedArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSavedNews(savedArticle: SavedArticle)

    @Query("Delete from saved_article where id = :saveArticleId")
    fun deleteSavedNews(saveArticleId : Long)

    @Query("Select * from saved_article")
    fun getAllSavedNews() : Flow<List<SavedArticle>>

}