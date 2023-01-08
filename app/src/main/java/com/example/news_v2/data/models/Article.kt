package com.example.news_v2.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
data class Article(
    @PrimaryKey @field:SerializedName("id") val id : Long = 0,
    @field:SerializedName("source") val source: Source? = Source(),
    @field:SerializedName("author") val author: String? = "",
    @field:SerializedName("title") val title: String? = "",
    @field:SerializedName("description") val description: String? = "",
    @field:SerializedName("url") val url: String? = "",
    @field:SerializedName("urlToImage") val urlToImage: String? = "",
    @field:SerializedName("publishedAt") val publishedAt: String? = "",
    @field:SerializedName("content") val content : String? = ""
)
