package com.erenalparslan.cookingapp.util

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object AddRecipe : Screen("addRecipe")
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
    object Cook : Screen("cook")
}