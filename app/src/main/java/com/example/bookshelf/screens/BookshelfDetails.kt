package com.example.bookshelf.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.network.BookModel

@Composable
fun DetailsScreen(
    bookModel: BookModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
            .data(bookModel.volumeInfo?.imageLinks?.thumbnail
            ?.replace("http","https")
                )
            .build(),
        contentDescription = null,
        modifier = modifier.size(150.dp)
        )
        bookModel.volumeInfo?.title?.let { BookEntry(content = "Title: $it") }
        //bookModel.volumeInfo?.authors?.let { Text(text = it) }
        bookModel.volumeInfo?.description?.let { BookEntry(content = "Description: $it") }
    }
}

@Composable
fun BookEntry(content: String) {
    Text(
        text = content,
        modifier = Modifier.padding(top = 8.dp)
    )
}