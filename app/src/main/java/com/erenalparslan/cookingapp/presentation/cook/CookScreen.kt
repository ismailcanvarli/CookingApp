package com.erenalparslan.cookingapp.presentation.cook

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.createSavedStateHandle

@Composable
fun CookScreen() {
    val viewModel = hiltViewModel<CookViewModel>()
    Surface {
        Column {
            Text(text = viewModel.cook!!)
        }
    }

}