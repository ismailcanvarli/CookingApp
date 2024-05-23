package com.erenalparslan.cookingapp.presentation.cook

import com.erenalparslan.cookingapp.domain.model.Cook

data class CookListState(
    val isLoading: Boolean = false,

    val cookList: List<Cook> = emptyList(),
)
