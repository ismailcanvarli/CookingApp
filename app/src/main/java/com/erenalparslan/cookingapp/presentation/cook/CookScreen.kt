package com.erenalparslan.cookingapp.presentation.cook

import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.erenalparslan.cookingapp.presentation.components.CookItem
import com.erenalparslan.cookingapp.presentation.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<CookViewModel>()


    val state by rememberUpdatedState(newValue = viewModel.cookState.collectAsState().value)

    Log.d("erenss", "CookScreen:${state.toString()} ")

    Surface {
        Column {

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBackIos, contentDescription = "Back")
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
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }
            if (state.isError) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Sorry,an error occurred")
                }
            }


            SearchBar(
                text = "",
                readOnly = false,
                onValueChange = {},
                modifier = Modifier.padding(10.dp)
            )
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