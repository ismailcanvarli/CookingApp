package com.erenalparslan.cookingapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class GetProfileResponse(
    @SerializedName("email")
    @Expose
    var email: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("recipes")
    @Expose
    var recipes: List<CookDtoItem?>? = null,
    @SerializedName("surname")
    @Expose
    var surname: String? = null
)