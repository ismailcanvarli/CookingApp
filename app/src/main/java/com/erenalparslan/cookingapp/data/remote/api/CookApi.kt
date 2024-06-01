package com.erenalparslan.cookingapp.data.remote.api

import com.erenalparslan.cookingapp.data.remote.response.CookDto
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CookApi {

    // recipe operations
    @GET("/v1/recipes")
    suspend fun getRecipe(): CookDto

    @POST("/v1/recipes")
    suspend fun addRecipe(): CookDto

    @POST("/v1/recipes/{recipeId}/save")
    suspend fun saveRecipe(@Path("recipeId") recipeId: Int): CookDto

    @DELETE("/v1/recipes/{recipeId}/save")
    suspend fun unsaveRecipe(@Path("recipeId") recipeId: Int): CookDto

    @POST("/v1/recipes/{recipeId}/like")
    suspend fun likeRecipe(@Path("recipeId") recipeId: Int): CookDto

    @DELETE("/v1/recipes/{recipeId}/like")
    suspend fun unlikeRecipe(@Path("recipeId") recipeId: Int): CookDto

    @GET("/v1/recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): CookDtoItem

    @DELETE("/v1/recipes/{id}")
    suspend fun deleteRecipe(@Path("id") id: Int): CookDto

    @GET("/v1/recipes/search")
    suspend fun searchRecipes(
        @Query("foodName") foodName: String,
        @Query("category") category: String?=null
    ): CookDto

    @GET("/v1/recipes/saved")
    suspend fun getSavedRecipes(): CookDto

    @GET("/v1/recipes/recipes-of-the-day")
    suspend fun getRecipeOfTheDay(): CookDto

    @GET("/v1/recipes//liked")
    suspend fun getLikedRecipes(): CookDto

    @GET(("/v1/recipes/instructionDetail"))
    suspend fun getInstructionDetail(): CookDto

    @GET("/v1/recipes/category/{category}")
    suspend fun getRecipeByCategory(@Path("category") category: String): CookDto

    //member operations
    @POST("/v1/members/register")
    suspend fun register(): CookDto

    @POST("/v1/members/login")
    suspend fun login(): CookDto

    @POST("/v1/members/login/google")
    suspend fun googleLogin(): CookDto

    @GET("/v1/members")
    suspend fun getProfile(): CookDto

    @GET("/v1/members/{memberId}")
    suspend fun getProfileById(@Path("memberId") memberId: Int): CookDto

    @GET("/v1/members/{memberId}/recipes")
    suspend fun getProfileRecipes(@Path("memberId") memberId: Int): CookDto

    // image upload operations
    @POST("/v1/images/upload")
    suspend fun uploadImage(): CookDto

    companion object {

        const val BASE_URL = "https://tracker-wholesale-divorce-holes.trycloudflare.com"
    }
}
