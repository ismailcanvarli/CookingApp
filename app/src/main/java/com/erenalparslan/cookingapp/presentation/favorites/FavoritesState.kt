package com.erenalparslan.cookingapp.presentation.favorites

import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem

data class FavoritesState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val cookList: List<CookDtoItem> = emptyList(),
)
