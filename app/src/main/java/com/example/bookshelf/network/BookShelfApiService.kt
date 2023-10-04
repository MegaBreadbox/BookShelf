package com.example.bookshelf.network


import retrofit2.http.GET
import retrofit2.http.Query



interface BookShelfApiService {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") q : String
    ): Bookshelf
}