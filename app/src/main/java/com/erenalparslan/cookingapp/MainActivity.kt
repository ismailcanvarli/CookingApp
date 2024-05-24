package com.erenalparslan.cookingapp

import ProfileScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erenalparslan.cookingapp.presentation.addRecipe.AddRecipeScreen
import com.erenalparslan.cookingapp.presentation.cook.CookScreen
import com.erenalparslan.cookingapp.presentation.favorites.FavoritesScreen
import com.erenalparslan.cookingapp.presentation.home.HomeScreen
import com.erenalparslan.cookingapp.presentation.search.SearchScreen
import com.erenalparslan.cookingapp.ui.theme.CookingAppTheme
import com.erenalparslan.cookingapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(bottomBar = { BottomNavigationBar(bottomNavController = navController) }) {

                        // Scaffold content
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            // NavHost provides in-app navigation.
                            NavHost(
                                navController = navController, startDestination = Screen.Home.route
                            ) {
                                // Home screen
                                composable(Screen.Home.route)  {
                                    HomeScreen(navController)
                                }
                                // Search screen
                                composable(Screen.Search.route) {
                                    SearchScreen()
                                }
                                // Recipe screen
                                composable(Screen.AddRecipe.route) {
                                    AddRecipeScreen()
                                }
                                // Favorites screen
                                composable(Screen.Favorites.route) {
                                    FavoritesScreen()
                                }
                                // Login screen
                                composable(Screen.Profile.route) {
                                    ProfileScreen()
                                }
                                composable(Screen.Cook.route+ "/{cookName}",
                                    arguments = listOf(
                                        navArgument("cookName") { type = NavType.StringType }
                                    )) {
                                    CookScreen()
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(
        bottomNavController: NavHostController
    ) {

        // Navigation bar items
        val items = listOf(
            BottomItem(
                title = stringResource(R.string.home), icon = Icons.Rounded.Home
            ),
            BottomItem(
                title = stringResource(R.string.search), icon = Icons.Rounded.Search
            ),
            BottomItem(
                title = stringResource(R.string.addRecipe), icon = Icons.Rounded.Add
            ),
            BottomItem(
                title = stringResource(R.string.favorite), icon = Icons.Rounded.Star
            ),
            BottomItem(
                title = stringResource(R.string.profile), icon = Icons.Rounded.Person
            ),
        )

        // Create a state to remember which item is selected.
        val selected = rememberSaveable {
            mutableIntStateOf(0)
        }

        // NavigationBar creates the bottom navigation bar.
        NavigationBar {
            // Row layout for navigation items
            Row(
                modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                items.forEachIndexed { index, bottomItem ->
                    // Each navigation bar item
                    NavigationBarItem(selected = selected.intValue == index, onClick = {
                        // Actions to take when an item is clicked
                        selected.intValue = index
                        when (selected.intValue) {
                            0 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.Home.route)
                            }

                            1 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.Search.route)
                            }

                            2 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.AddRecipe.route)
                            }

                            3 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.Favorites.route)
                            }

                            4 -> {
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.Profile.route)
                            }

                        }
                    }, icon = {
                        // Icon for the item
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }, label = {
                        // Label for the item
                        Text(
                            text = bottomItem.title, color = MaterialTheme.colorScheme.onBackground
                        )
                    })
                }
            }
        }
    }

    data class BottomItem(
        val title: String, val icon: ImageVector
    )
}

// Preview the bottom navigation bar
@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() {
    val items = listOf(
        MainActivity.BottomItem(
            title = "Home", icon = Icons.Rounded.Home
        ),
        MainActivity.BottomItem(
            title = "Search", icon = Icons.Rounded.Search
        ),
        MainActivity.BottomItem(
            title = "Add Recipe", icon = Icons.Rounded.Add
        ),
        MainActivity.BottomItem(
            title = "Favorites", icon = Icons.Rounded.Star
        ),
        MainActivity.BottomItem(
            title = "Profile", icon = Icons.Rounded.Person
        ),
    )

    MainActivity().BottomNavigationBar(rememberNavController())
}
