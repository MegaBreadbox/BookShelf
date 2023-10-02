package com.example.bookshelf.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.internal.ignoreIoExceptions
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private val customJson = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
}

private const val BASE_URL =
    "https://www.googleapis.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(customJson.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface BookShelfApiService {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") q : String
    ): Bookshelf
}



object BookshelfApi {
    val retrofitService : BookShelfApiService by lazy {
        retrofit.create(BookShelfApiService::class.java)
    }
}