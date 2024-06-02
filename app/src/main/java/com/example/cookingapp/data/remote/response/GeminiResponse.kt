package com.example.cookingapp.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeminiResponse(
    @SerializedName("explanation") @Expose var explanation: String? = null
)