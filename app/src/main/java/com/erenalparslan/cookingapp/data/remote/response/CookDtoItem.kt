package com.erenalparslan.cookingapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class CookDtoItem(
    @SerializedName("category")
    @Expose
    var category: String? = null,
    @SerializedName("cookingTime")
    @Expose
    var cookingTime: String? = null,
    @SerializedName("difficultyLevel")
    @Expose
    var difficultyLevel: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null,
    @SerializedName("ingredients")
    @Expose
    var ingredients: List<Ingredient?>? = null,
    @SerializedName("instructions")
    @Expose
    var instructions: List<Instruction?>? = null,
    @SerializedName("preparationTime")
    @Expose
    var preparationTime: String? = null,
    @SerializedName("recipeName")
    @Expose
    var recipeName: String? = null,
    @SerializedName("likeCount")
    @Expose
    var likeCount: Int? = null,
    @SerializedName("servesFor")
    @Expose
    var servesFor: String? = null,
    @SerializedName("totalTime")
    @Expose
    var totalTime: String? = null
)