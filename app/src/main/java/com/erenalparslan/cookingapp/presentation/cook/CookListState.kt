package com.erenalparslan.cookingapp.presentation.cook

import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import com.erenalparslan.cookingapp.domain.model.Cook

data class CookListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val cookList: List<CookDtoItem> = emptyList(),
)
