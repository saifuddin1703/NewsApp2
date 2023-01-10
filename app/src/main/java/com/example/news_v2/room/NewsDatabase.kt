package com.example.news_v2.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news_v2.data.models.Article
import com.example.news_v2.data.models.RemoteKeys
import com.example.news_v2.data.models.SavedArticle
import com.example.news_v2.room.daos.NewsDao
import com.example.news_v2.room.daos.RemoteKeysDao
import com.example.news_v2.room.daos.SavedNewsDao
import com.example.news_v2.utils.TAG


@androidx.room.Database(
    entities = [Article::class, RemoteKeys::class,SavedArticle::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MyTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
    abstract fun getRemoteKeysDao() : RemoteKeysDao
    abstract fun getSavedNewsDao() : SavedNewsDao

    companion object {

        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): NewsDatabase {
                return Room.databaseBuilder(
                    context,
                    NewsDatabase::class.java, "News"
                )
                    .addTypeConverter(MyTypeConverter())
                    .build()
        }
    }
}