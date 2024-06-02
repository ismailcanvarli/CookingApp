package com.erenalparslan.cookingapp.presentation.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.erenalparslan.cookingapp.presentation.cook.CookViewModel


@Composable
fun FavoritesScreen() {
    val viewModel = hiltViewModel<FavoritesViewModel>()
    val state by rememberUpdatedState(newValue = viewModel.cookState.collectAsState().value)
    var selectedTab by remember { mutableStateOf(0) }

    val tabs = listOf("Kaydedilenler", "Beğenilenler")

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title, fontSize = 24.sp) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index })
                }
            }

            LazyColumn {

                items(state.cookList.size) {
                    val item = state.cookList[it]

                    Card(
                        modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                //TODO bu kısımdaki linK görsellerin linki olmalı.
                                painter = rememberAsyncImagePainter("https://www.pexels.com/photo/flat-lay-photography-of-vegetable-salad-on-plate-1640777/"),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(128.dp)
                                    .width(128.dp)
                                    .weight(0.5f)
                            )

                            //TODO bu kısımda yapılması gerekiyor.
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .weight(0.5f)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Yiyecek Adı",
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                }

                                Row {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            Icons.Default.Star, contentDescription = null
                                        ) // Beğenilme sayısı ikonu
                                        Text("10", fontSize = 12.sp) // Beğenilme sayıs��nı kullanın
                                    }

                                    Spacer(Modifier.width(6.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            Icons.Default.Timer, contentDescription = null
                                        ) // Hazırlanma süresi ikonu
                                        Text(
                                            "30 dk",
                                            fontSize = 12.sp
                                        ) // Hazırlanma süresini kullanın
                                    }

                                    Spacer(Modifier.width(6.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            Icons.Default.Book, contentDescription = null
                                        ) // Zorluk seviyesi ikonu
                                        Text(
                                            "Kolay",
                                            fontSize = 12.sp
                                        ) // Zorluk seviyesini kullanın
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoritesScreen() {
    FavoritesScreen()
}