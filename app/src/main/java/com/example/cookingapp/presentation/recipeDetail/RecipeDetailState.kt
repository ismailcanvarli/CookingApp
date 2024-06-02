package com.example.cookingapp.presentation.recipeDetail

import com.example.cookingapp.data.remote.response.CookDtoItem

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val cook: CookDtoItem? = null,
)
