package com.example.cookingapp.presentation.profile.state

import com.example.cookingapp.data.remote.response.GetProfileResponse

data class ProfileState(
    val isLoading: Boolean = false,
    val isError: Boolean? = null,
    val isSuccess: Boolean = false,
    val isLogin: Boolean? = false,
    val profile: GetProfileResponse? = null
)
