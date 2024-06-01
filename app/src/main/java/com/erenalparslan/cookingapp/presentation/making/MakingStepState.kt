package com.erenalparslan.cookingapp.presentation.making

import com.erenalparslan.cookingapp.data.remote.response.Instruction

data class MakingStepState(
    val list: List<Instruction?>? = null,
    val question: String? = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
