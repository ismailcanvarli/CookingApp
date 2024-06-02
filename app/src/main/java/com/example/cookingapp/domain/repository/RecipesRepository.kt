package com.example.cookingapp.domain.repository

import com.example.cookingapp.data.remote.response.CookDtoItem
import com.example.cookingapp.data.remote.response.GetProfileResponse
import com.example.cookingapp.data.remote.response.LoginDto
import com.example.cookingapp.data.remote.response.LoginResponse
import com.example.cookingapp.data.remote.response.RegisterDto
import com.example.cookingapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun getRecipesByCategory(category: String): Flow<Resource<List<CookDtoItem>>>
    suspend fun getRecipes(): Flow<Resource<List<CookDtoItem>>>
    suspend fun getRecipesByName(foodName: String): Flow<Resource<List<CookDtoItem>>>
    suspend fun getRecipesById(id: Int): Flow<Resource<CookDtoItem>>
    suspend fun register(member: RegisterDto): Flow<Resource<String>>
    suspend fun login(member: LoginDto): Flow<Resource<LoginResponse>>
    suspend fun askGemini(question: String): Flow<Resource<String>>
    suspend fun getProfile(token: String): Flow<Resource<GetProfileResponse>>
    suspend fun getLikedRecipes(token: String): Flow<Resource<List<CookDtoItem>>>

}