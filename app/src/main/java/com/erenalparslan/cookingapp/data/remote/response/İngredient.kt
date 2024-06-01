package com.erenalparslan.cookingapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Ingredient(
    @SerializedName("amount")
    @Expose
    var amount: String? = null,
    @SerializedName("ingredient")
    @Expose
    var ingredient: String? = null
)