package com.example.bookshelf.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(Modifier.weight(.25F))
        Text(text = "Search for books")
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = inputViewModel.inputUiState,
            singleLine = true,
            onValueChange = { inputViewModel.changeInput(it) },
            label = { Text(text = "Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onKeyboardSearch() }
            )
        )
        Spacer(Modifier.weight(.75F))
    }
}