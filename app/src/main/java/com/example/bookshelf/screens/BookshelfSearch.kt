package com.example.bookshelf.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
@Composable
fun SearchScreen(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
) {
    when(val bookUiState = bookshelfViewModel.bookshelfUiState) {
        is BookUiState.Success -> SearchBar(stringState = bookUiState)
        is BookUiState.Loading -> Text(text = "Loading")
        is BookUiState.Error -> Text(text = "Error")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    stringState: BookUiState.Success,
    modifier: Modifier = Modifier
) {
    Text(text = "Search for books")
    TextField(
        value = stringState.textInput,
        onValueChange = { stringState.textInput = it},
        label = { Text(text ="Search") }
    )
}