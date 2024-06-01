package com.erenalparslan.cookingapp.presentation.recipeDetail

import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val cook: CookDtoItem?=null,
)
