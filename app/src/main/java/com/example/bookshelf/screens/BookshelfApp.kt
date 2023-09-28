package com.example.bookshelf.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.network.Bookshelf

@Composable
fun BookShelfApp(
    bookshelfViewModel: BookshelfViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    when(val bookUiState = bookshelfViewModel.bookshelfUiState) {
        is BookUiState.Success -> BookShelfCard(bookUiState.bookShelf, {})
        is BookUiState.Loading -> Text(text = "Loading")
        is BookUiState.Error -> Text(text = "Error")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfCard(
    bookshelf: Bookshelf,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        onClick = onClick
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(bookshelf.items[0].volumeInfo.imageLinks.thumbnail
                    .replace("http","https")
                )
                .build(),
            contentDescription = null,
            modifier = modifier.fillMaxWidth()
        )
    }

}