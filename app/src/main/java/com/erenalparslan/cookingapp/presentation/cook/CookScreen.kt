package com.erenalparslan.cookingapp.presentation.cook

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

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

@Preview(showBackground = true)
@Composable
fun PreviewCookScreen() {
    CookScreen()
}