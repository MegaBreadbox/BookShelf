package com.example.bookshelf.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.viewmodels.BookshelfViewModel
import com.example.bookshelf.viewmodels.TextInputViewModel

enum class BookshelfScreen() {
    Search,
    Bookshelf,
    Details
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfMenu(
    navController: NavHostController = rememberNavController(),
    bookshelfViewModel: BookshelfViewModel = viewModel(),
    inputViewModel: TextInputViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Scaffold(){
        NavHost(
            navController = navController,
            startDestination = BookshelfScreen.Search.name,
            modifier = Modifier.padding(it)

        ){
            composable(BookshelfScreen.Search.name) {
                SearchScreen(inputViewModel = inputViewModel,
                    onKeyboardSearch = {
                        navController.navigate(BookshelfScreen.Bookshelf.name)
                        bookshelfViewModel.getBooks(inputViewModel.inputUiState)
                    }
                )
            }
            composable(BookshelfScreen.Bookshelf.name) {
                BookShelfApp(bookshelfViewModel = bookshelfViewModel)
            }
        }
    }
}
