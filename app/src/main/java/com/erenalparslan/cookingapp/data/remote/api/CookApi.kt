package com.erenalparslan.cookingapp.data.remote.api

import com.erenalparslan.cookingapp.data.remote.response.CookDto
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import com.erenalparslan.cookingapp.data.remote.response.GeminiResponse
import com.erenalparslan.cookingapp.data.remote.response.GetProfileResponse
import com.erenalparslan.cookingapp.data.remote.response.Instruction
import com.erenalparslan.cookingapp.data.remote.response.LoginDto
import com.erenalparslan.cookingapp.data.remote.response.LoginResponse
import com.erenalparslan.cookingapp.data.remote.response.RegisterDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
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
        @Query("category") category: String? = null
    ): CookDto

    @GET("/v1/recipes/saved")
    suspend fun getSavedRecipes(): CookDto

    @GET("/v1/recipes/recipes-of-the-day")
    suspend fun getRecipeOfTheDay(): CookDto

    @GET("/v1/recipes/liked")
    suspend fun getLikedRecipes(@Header("Authorization") token: String): List<CookDtoItem>

    @GET(("/v1/recipes/instructionDetail"))
    suspend fun getInstructionDetail(): CookDto

    @GET("/v1/recipes/category/{category}")
    suspend fun getRecipeByCategory(@Path("category") category: String): CookDto

    //member operations
    @POST("/v1/members/register")
    suspend fun register(@Body member: RegisterDto): String

    @POST("/v1/members/login")
    suspend fun login(@Body member: LoginDto): LoginResponse

    @POST("/v1/members/login/google")
    suspend fun googleLogin(): CookDto

    @GET("/v1/members")
    suspend fun getProfile(@Header("Authorization") token: String): GetProfileResponse

    @GET("/v1/members/{memberId}")
    suspend fun getProfileById(@Path("memberId") memberId: Int): CookDto

    @GET("/v1/members/{memberId}/recipes")
    suspend fun getProfileRecipes(@Path("memberId") memberId: Int): CookDto

    // image upload operations
    @POST("/v1/images/upload")
    suspend fun uploadImage(): CookDto

    @GET("/v1/recipes/instructionDetail")
    suspend fun askGemini(@Query("instruction") instruction: String): GeminiResponse

    companion object {

        const val BASE_URL = "https://hit-harvest-gmt-screw.trycloudflare.com"
    }
}
