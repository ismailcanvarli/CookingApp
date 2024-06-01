package com.erenalparslan.cookingapp.presentation.cook

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.erenalparslan.cookingapp.presentation.components.CookItem
import com.erenalparslan.cookingapp.presentation.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookScreen(navHostController: NavHostController, isSearch: Boolean = false) {
    val viewModel = hiltViewModel<CookViewModel>()
    val state by rememberUpdatedState(newValue = viewModel.cookState.collectAsState().value)
    var searchQuery by remember { mutableStateOf("") }


    Surface {
        Column {
            if (!isSearch) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterStart),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { navHostController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBackIos,
                                contentDescription = "Back"
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = viewModel.cook,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }


            SearchBar(
                text = searchQuery,
                onValueChange = { query ->
                    searchQuery = query
                    viewModel.getRecipesByFoodName(query)
                },readOnly = false, modifier = Modifier.padding(10.dp)
            )

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            if (state.isError) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Sorry,an error occurred")
                }
            }


            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(state.cookList.size) {
                    val item = state.cookList[it]
                    CookItem(cook = item, navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun RecipeMakingScreen(navHostController: NavHostController) {
    //TODO bu kısımda apiden gelen değer olacak.
    // Tarifin aşamalarını bir liste olarak saklayalım
    val recipeSteps = listOf("Aşama 1", "Aşama 2", "Aşama 3", "Aşama 4", "Aşama 5")
    var currentStep by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Şu anki aşamayı gösteren metin
        Text(
            text = recipeSteps[currentStep],
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    // Geri butonuna tıklandığında bir önceki aşamaya geç
                    if (currentStep > 0) currentStep--
                }, modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Geri")
            }
            Button(
                onClick = {
                    // İleri butonuna tıklandığında bir sonraki aşamaya geç
                    if (currentStep < recipeSteps.lastIndex) currentStep++
                }, modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = "İleri")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeMakingScreen() {
    val navHostController = rememberNavController()
    RecipeMakingScreen(navHostController = navHostController)
}