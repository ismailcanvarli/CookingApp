package com.example.cookingapp.presentation.favorites

import com.example.cookingapp.data.remote.response.CookDtoItem

data class FavoritesState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val cookList: List<CookDtoItem> = emptyList(),
)
