package com.erenalparslan.cookingapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.erenalparslan.cookingapp.R
import com.erenalparslan.cookingapp.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController? = null) {
    val menuItems = listOf(
        Pair(R.drawable.soup, stringResource(R.string.soup)),
        Pair(R.drawable.main_food, stringResource(R.string.main_course)),
        Pair(R.drawable.sweet, stringResource(R.string.dessert)),
        Pair(R.drawable.drink, stringResource(R.string.drink))
    )
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val itemHeight = (screenHeight / 2 - 104.dp)
    Surface(
        modifier = Modifier.fillMaxHeight(),

    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
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
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                items(menuItems.size) { index ->
                    val menuItem = menuItems[index]
                    //TODO buradaki height ve diğer şeyler tekrar ayarlanacak.
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(itemHeight),
                        onClick = { navController?.navigate(Screen.Cook.route + "/${menuItem.second}") }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth() // Fill the width of the parent
                                .padding(6.dp) // Apply padding around the box
                                .fillMaxHeight(), // Fill the height of the parent
                            contentAlignment = Alignment.Center
                        ) {
                            // Image with rounded corners
                            Image(
                                modifier = Modifier
                                    .fillMaxSize() // Fill the entire Box
                                    .clip(RoundedCornerShape(22.dp)),
                                painter = painterResource(id = menuItem.first),
                                contentDescription = menuItem.second,
                                contentScale = ContentScale.Crop,
                            )

                            // Text displayed at the center of the Box
                            Text(
                                text = menuItem.second,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(16.dp) // Padding around the text
                                    .background(Color.White) // Background color behind the text
                                    .align(Alignment.BottomCenter) // Align text to the bottom center
                                    .fillMaxWidth(), // Fill the width of the parent
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
    HomeScreen()
}