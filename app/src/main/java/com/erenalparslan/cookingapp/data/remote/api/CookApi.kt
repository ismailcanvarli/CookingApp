package com.erenalparslan.cookingapp.data.remote.api

import com.erenalparslan.cookingapp.data.remote.response.CookDto

interface CookApi {

    suspend fun getCook() :CookDto
companion object{

    const val BASE_URL=""
}
}
