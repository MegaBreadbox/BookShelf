package com.example.bookshelf.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.R
import com.example.bookshelf.network.BookModel
import com.example.bookshelf.viewmodels.BookshelfViewModel
import com.example.bookshelf.viewmodels.TextInputViewModel

enum class BookshelfScreen() {
    Search,
    Bookshelf,
    Details
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfMenu(
    navController: NavHostController = rememberNavController(),
    bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory),
    inputViewModel: TextInputViewModel = viewModel(),
) {
    Scaffold(
        topBar = {
            BookshelfTopBar()
        }
    ){ padding ->
        NavHost(
            navController = navController,
            startDestination = BookshelfScreen.Search.name,
            modifier = Modifier.padding(padding)

        ){
            lateinit var bookDetails: BookModel

            composable(BookshelfScreen.Search.name) {
                SearchScreen(inputViewModel = inputViewModel,
                    onKeyboardSearch = {
                        navController.navigate(BookshelfScreen.Bookshelf.name)
                        bookshelfViewModel.getBooks(inputViewModel.inputUiState)
                    }
                )
            }
            composable(BookshelfScreen.Bookshelf.name) {
                BookShelfApp(
                    bookshelfViewModel = bookshelfViewModel,
                    onClick = {
                        navController.navigate(BookshelfScreen.Details.name)
                        bookDetails = it
                    }
                )
            }

            composable(BookshelfScreen.Details.name) {
                DetailsScreen(
                    bookModel = bookDetails
                )
            }
        }
    }
}
