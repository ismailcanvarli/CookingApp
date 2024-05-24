package com.erenalparslan.cookingapp.presentation.cook

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.createSavedStateHandle
import com.erenalparslan.cookingapp.data.remote.response.CookDto
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import kotlinx.coroutines.runBlocking

@Composable
fun CookScreen() {
    val viewModel = hiltViewModel<CookViewModel>()


    val state by rememberUpdatedState(newValue = viewModel.cookState.collectAsState().value)

    Log.d("erenss", "CookScreen:${state.toString()} ")

    Surface {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.isError) {
            Text(text = "Sorry,an error occurred")
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(state.cookList.size) {
                val item = state.cookList[it]
                Text(text = item.recipeName ?: "null")
            }

        }
    }

}