package com.example.cookingapp.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Instruction(
    @SerializedName("instruction") @Expose var instruction: String? = null,
    @SerializedName("time") @Expose var time: String? = null
)