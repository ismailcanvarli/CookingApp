package com.erenalparslan.cookingapp.data.remote.api

import com.erenalparslan.cookingapp.data.remote.response.CookDto
import retrofit2.http.GET

interface CookApi {

    @GET("v1/recipes")
    suspend fun getCook(): CookDto

    companion object {

        const val BASE_URL = "https://identifier-inner-preparation-rug.trycloudflare.com/"
    }
}
