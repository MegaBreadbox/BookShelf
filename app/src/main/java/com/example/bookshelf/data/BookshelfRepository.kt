package com.example.bookshelf.data

import com.example.bookshelf.network.BookShelfApiService
import com.example.bookshelf.network.Bookshelf

interface BookshelfRepository {
    suspend fun getBooks(input: String): Bookshelf
}

class NetworkBookshelfRepository(
    private val bookShelfApiService: BookShelfApiService
): BookshelfRepository {
    override suspend fun getBooks(input: String): Bookshelf = bookShelfApiService.getBooks(input)
}