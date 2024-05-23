package com.erenalparslan.cookingapp.presentation.cook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.createSavedStateHandle
import com.erenalparslan.cookingapp.data.remote.response.CookDto
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import kotlinx.coroutines.runBlocking

@Composable
fun CookScreen() {
    val viewModel = hiltViewModel<CookViewModel>()
    Surface {
        val list = ArrayList<CookDtoItem>()
        runBlocking {
            viewModel.getRecipe().collect {
                it.forEach {
                    list.add(it)
                }
            }
        }


        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(list.size) {
                val item = list[it]
                Text(text = item.recipeName ?: "null")
            }

        }
    }

}