package com.example.bookshelf.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            alignment = Alignment.Center,
            modifier = modifier.size(150.dp).align(Alignment.CenterHorizontally)
        )
        bookModel.volumeInfo?.title?.let { BookEntry(content = "Title: $it") }
        bookModel.volumeInfo?.subtitle?.let { BookEntry(content = "Subtitle: $it") }
        bookModel.volumeInfo?.authors?.let { ArrayEntry(content = "Authors:", it) }
        bookModel.volumeInfo?.publisher?.let { BookEntry(content = "Publisher: $it") }
        bookModel.volumeInfo?.publishedDate?.let { BookEntry(content = "Published date: $it") }
        bookModel.volumeInfo?.description?.let { BookEntry(content = "Description: $it") }
    }
}

@Composable
fun BookEntry(content: String) {
    Text(
        text = content,
        modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)
    )
}
@Composable
fun ArrayEntry(content: String, data: Array<String>) {
    Text(
        text = "$content ${data.joinToString(",")}",
        modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)
        
    )
}

