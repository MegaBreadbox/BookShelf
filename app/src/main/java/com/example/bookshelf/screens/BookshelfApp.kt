package com.example.bookshelf.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.network.BookModel
import com.example.bookshelf.network.Bookshelf
import com.example.bookshelf.viewmodels.BookUiState
import com.example.bookshelf.viewmodels.BookshelfViewModel

@Composable
fun BookShelfApp(
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier
){
    when(val bookUiState = bookshelfViewModel.bookshelfUiState) {
        is BookUiState.Success -> BookshelfGrid(bookUiState.bookShelf, {})
        is BookUiState.Loading -> Text(text = "Loading")
        is BookUiState.Error -> Text(text = "Error")
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookshelfGrid(
    bookshelf: Bookshelf,
    onClick: () -> Unit
) {
    val bookArray = bookshelf.items
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)

    ) {
        items(bookArray) {entry -> entry
            BookShelfCard(
                entry, onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfCard(
    bookModel: BookModel,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        onClick = onClick
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(bookModel.volumeInfo.imageLinks.thumbnail
                    .replace("http","https")
                )
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
    }

}