package com.example.cookingapp.presentation.cook

import com.example.cookingapp.data.remote.response.CookDtoItem

data class CookListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val cookList: List<CookDtoItem> = emptyList(),
)
