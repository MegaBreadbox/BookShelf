package com.example.bookshelf.data

import com.example.bookshelf.network.BookShelfApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bookshelfRepository: BookshelfRepository
}

class DefaultAppContainer : AppContainer {

    @OptIn(ExperimentalSerializationApi::class)
    private val customJson = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val baseUrl =
        "https://www.googleapis.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(customJson.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : BookShelfApiService by lazy {
        retrofit.create(BookShelfApiService::class.java)
    }

    override val bookshelfRepository: BookshelfRepository by lazy{
        NetworkBookshelfRepository(retrofitService)
    }
}
