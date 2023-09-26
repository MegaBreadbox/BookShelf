package com.example.bookshelf.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BookShelfApp(
    bookshelfViewModel: BookshelfViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    when(bookshelfViewModel.bookshelfUiState) {
        is BookUiState.Success -> Text(text = bookshelfViewModel.bookshelfUiState.toString())
        is BookUiState.Loading -> Text(text = "Loading")
        is BookUiState.Error -> Text(text = "Error")
    }
}

@Composable
fun BookShelfCard(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier){

}