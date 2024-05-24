package com.erenalparslan.cookingapp.data.remote.response


import com.erenalparslan.cookingapp.util.Resource
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CookDto(
    val recipesList: List<CookDtoItem>
)