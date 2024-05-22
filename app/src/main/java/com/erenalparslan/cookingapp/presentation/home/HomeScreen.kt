package com.erenalparslan.cookingapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.erenalparslan.cookingapp.R
import com.erenalparslan.cookingapp.util.Screen

@Composable
fun HomeScreen(navController: NavController) {
    HomeScreenContent { index ->
        when (index) {
            0 -> navController.navigate(Screen.Soup.route)
            1 -> navController.navigate(Screen.MainCourse.route)
            2 -> navController.navigate(Screen.Dessert.route)
            3 -> navController.navigate(Screen.Drink.route)
        }
    }
}

@Composable
fun HomeScreenContent(onCardClick: (Int) -> Unit = {}) {
    val menuItems = listOf(
        Pair(R.drawable.ic_launcher_background, stringResource(R.string.soup)),
        Pair(R.drawable.ic_launcher_background, stringResource(R.string.main_course)),
        Pair(R.drawable.ic_launcher_background, stringResource(R.string.dessert)),
        Pair(R.drawable.ic_launcher_background, stringResource(R.string.drink))
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.home_page),
                fontSize = 32.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                items(menuItems.size) { index ->
                    val menuItem = menuItems[index]
                    //TODO buradaki height ve diğer şeyler tekrar ayarlanacak.
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(280.dp)
                            .clickable {
                                onCardClick(index)
                            },
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(id = menuItem.first),
                                contentDescription = menuItem.second,
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                text = menuItem.second,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreenContent()
}