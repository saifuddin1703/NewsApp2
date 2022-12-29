package com.example.news_v2.retrofit

import com.example.news_v2.services.NewsService
import com.example.news_v2.utils.API_KEY
import com.example.news_v2.utils.BASE_URL
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object APIClient {
    private var interceptor = HttpLoggingInterceptor()
    private lateinit var retrofit: retrofit2.Retrofit

    init {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
                // interceptor for adding api to every request
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()
                    val originalHttpUrl: HttpUrl = original.url
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apiKey", API_KEY)
                        .build()

                    // Request customization: add request headers
                    val requestBuilder: Request.Builder = original.newBuilder()
                        .url(url)
                    val request: Request = requestBuilder.build()
                    return chain.proceed(request)
                }
            })
            .build()



        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getNewsService(): NewsService {
        return retrofit.create(NewsService::class.java)
    }

}