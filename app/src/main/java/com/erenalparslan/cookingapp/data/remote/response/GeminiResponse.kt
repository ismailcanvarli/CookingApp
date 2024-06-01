package com.erenalparslan.cookingapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class GeminiResponse(
    @SerializedName("explanation")
    @Expose
    var explanation: String? = null
)