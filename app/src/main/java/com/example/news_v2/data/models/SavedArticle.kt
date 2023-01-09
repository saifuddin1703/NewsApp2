package com.example.news_v2.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "saved_article")
data class SavedArticle(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id : Long = 0,
    @ColumnInfo(name = "source") val source: Source? = Source(),
    @ColumnInfo(name = "author") val author: String? = "",
    @ColumnInfo(name = "title") val title: String? = "",
    @ColumnInfo(name = "description") val description: String? = "",
    @ColumnInfo(name = "url") val url: String = "",
    @ColumnInfo(name = "urlToImage") val urlToImage: String? = "",
    @ColumnInfo(name = "publishedAt") val publishedAt: String? = "",
    @ColumnInfo(name = "content") val content : String? = ""
)
