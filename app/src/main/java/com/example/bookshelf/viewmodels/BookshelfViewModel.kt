package com.example.bookshelf.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.network.Bookshelf
import com.example.bookshelf.network.BookshelfApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookUiState {
    data class Success(
        val bookShelf: Bookshelf,
    ) : BookUiState
    object Loading : BookUiState
    object Error : BookUiState
}

class BookshelfViewModel: ViewModel() {
    var bookshelfUiState : BookUiState by mutableStateOf(BookUiState.Loading)
        private set


    fun getBooks(input: String) {
        viewModelScope.launch {
            bookshelfUiState = try {
                val books = BookshelfApi.retrofitService.getBooks(input)
                BookUiState.Success(bookShelf = books)

            } catch (e: IOException) {
                BookUiState.Error
            }
        }
    }
}