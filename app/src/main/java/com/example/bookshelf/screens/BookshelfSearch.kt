package com.example.bookshelf.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.example.bookshelf.viewmodels.TextInputViewModel

@Composable
fun SearchScreen(
    inputViewModel: TextInputViewModel,
    onKeyboardSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(inputViewModel, onKeyboardSearch)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    inputViewModel: TextInputViewModel,
    onKeyboardSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(text = "Search for books")
        TextField(
            value = inputViewModel.inputUiState,
            maxLines = 1,
            onValueChange = { inputViewModel.changeInput(it) },
            label = { Text(text = "Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onKeyboardSearch() }
            )
        )
    }
}