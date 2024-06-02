package com.example.cookingapp.presentation.making

import com.example.cookingapp.data.remote.response.Instruction

data class MakingStepState(
    val list: List<Instruction?>? = null,
    val question: String? = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
