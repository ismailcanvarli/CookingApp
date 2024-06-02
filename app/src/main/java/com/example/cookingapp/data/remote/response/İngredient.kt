package com.example.cookingapp.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("amount") @Expose var amount: String? = null,
    @SerializedName("ingredient") @Expose var ingredient: String? = null
)