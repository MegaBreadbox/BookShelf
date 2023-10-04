package com.example.bookshelf.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookshelfRepository
import com.example.bookshelf.network.Bookshelf
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookUiState {
    data class Success(
        val bookShelf: Bookshelf,
    ) : BookUiState
    object Loading : BookUiState
    object Error : BookUiState
}

class BookshelfViewModel(private val bookshelfRepository: BookshelfRepository): ViewModel() {

    var bookshelfUiState : BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    fun getBooks(input: String) {
        viewModelScope.launch {
            bookshelfUiState = try {
                val result = bookshelfRepository.getBooks(input)
                BookUiState.Success(bookShelf = result)

            } catch (e: IOException) {
                BookUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
             initializer {
                 val application = (this[APPLICATION_KEY] as BookshelfApplication)
                 val bookshelfRepository = application.container.bookshelfRepository
                 BookshelfViewModel(bookshelfRepository = bookshelfRepository)
             }
        }
    }
}