package com.example.bookshelf.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class TextInputViewModel : ViewModel() {
    var inputUiState by mutableStateOf("")
        private set

    fun changeInput(input: String) {
        inputUiState = input
    }
}

