package com.example.news_v2.data.models

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news_v2.utils.JsonNavType
import com.example.news_v2.utils.NumberUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "article")
data class Article(
    @field:SerializedName("id") var id : Long = 0,
    @field:SerializedName("source") val source:@RawValue Source? = Source(),
    @field:SerializedName("author") val author: String? = "",
    @field:SerializedName("title") val title: String? = "",
    @field:SerializedName("description") val description: String? = "",
    @PrimaryKey @field:SerializedName("url") val url: String = "",
    @field:SerializedName("urlToImage") val urlToImage: String? = "",
    @field:SerializedName("publishedAt") val publishedAt: String? = "",
    @field:SerializedName("content") val content : String? = ""
):Parcelable{
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}

class ArticleArgType() : JsonNavType<Article>(){
    override fun fromJsonParse(value: String): Article {
       return Gson().fromJson(value,Article::class.java)
    }

    override fun Article.getJsonParse(): String {
        return Gson().toJson(this)
    }

}
