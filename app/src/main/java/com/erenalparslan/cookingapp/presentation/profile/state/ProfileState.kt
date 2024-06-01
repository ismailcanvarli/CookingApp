package com.erenalparslan.cookingapp.presentation.profile.state

data class ProfileState(
    val isLoading: Boolean? = null,
    val isError: Boolean? = null,
    val isSuccess: Boolean = false
)
