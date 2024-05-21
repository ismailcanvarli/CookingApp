package com.erenalparslan.cookingapp.util

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Favorites : Screen("favorites")
    object Search : Screen("search")
}