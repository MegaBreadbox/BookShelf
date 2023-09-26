package com.example.bookshelf.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "https://www.googleapis.com/books"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface BookShelfApiService {
    @GET("v1/volumes?q=history")
    suspend fun getBooks(): Bookshelf
}

object BookshelfApi {
    val retrofitService : BookShelfApiService by lazy {
        retrofit.create(BookShelfApiService::class.java)
    }
}