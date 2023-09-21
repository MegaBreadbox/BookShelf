package com.example.bookshelf.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

sealed interface Bingus

class BookshelfViewModel: ViewModel() {
    var bookshelfUiState by mutableStateOf("")
        private set


}