package com.erenalparslan.cookingapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Instruction(
    @SerializedName("instruction")
    @Expose
    var instruction: String? = null,
    @SerializedName("time")
    @Expose
    var time: String? = null
)