package com.example.news_v2.utils

data class Result<T>(val data : T? , val status : Status,val message : String?){
    companion object{
        fun <T> success(data: T?): Result<T> = Result(data = data, status = Status.SUCCESS, null)
        fun <T> error(message: String?): Result<T> =
            Result(data = null, status = Status.ERROR, message)

        fun <T> loading(): Result<T> = Result(data = null, status = Status.LOADING, null)
    }
}
