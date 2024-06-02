package com.example.cookingapp.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetProfileResponse(
    @SerializedName("email") @Expose var email: String? = null,
    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("name") @Expose var name: String? = null,
    @SerializedName("recipes") @Expose var recipes: List<CookDtoItem?>? = null,
    @SerializedName("surname") @Expose var surname: String? = null
)