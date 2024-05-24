package com.erenalparslan.cookingapp.data.remote.api

import com.erenalparslan.cookingapp.data.remote.response.CookDto
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.Locale.Category

interface CookApi {

    @GET("v1/recipes/{id}")
    suspend fun getRecipeById(@Path("id") id :Int): CookDto

    @GET("v1/recipes")
    suspend fun getCook(): CookDto

    @GET("v1/recipes/{category}")
    suspend fun getCookByCategory(@Path("category") category: String): CookDto

    @GET("v1/recipes/search")
    suspend fun searchRecipes(): CookDto

    @GET("v1/recipes/recipe-of-the-day")
    suspend fun getRecipeDaily(): CookDto

    @GET("v1/members/login/google")
    suspend fun getGoogleLogin(): CookDto

    @GET("v1/members")
    suspend fun getProfile(): CookDto

    @GET("v1/recipes/saved")
    suspend fun getSaveList(): CookDto

    @GET("v1/recipes/liked")

    suspend fun getLikeList(): CookDto

    @GET("/v1/recipes/{recipeId}/like")

    suspend fun recipeLikeList(@Path("recipeId") recipeId: Int): CookDto

    @GET("v1/recipes/{recipeId}/save")
    suspend fun recipeSave(@Path("recipeId") recipeId: Int): CookDto

    companion object {

        const val BASE_URL = "https://identifier-inner-preparation-rug.trycloudflare.com/"
    }
}
