package com.erenalparslan.cookingapp.domain.repository

import com.erenalparslan.cookingapp.data.remote.response.CookDto
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import com.erenalparslan.cookingapp.domain.model.Cook
import com.erenalparslan.cookingapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun getRecipesByCategory(category: String):Flow<Resource<List<CookDtoItem>>>
    suspend fun getRecipes():Flow<Resource<List<CookDtoItem>>>
    suspend fun getRecipesByName(foodName: String): Flow<Resource<List<CookDtoItem>>>
    suspend fun getRecipesById(id: Int): Flow<Resource<CookDtoItem>>
}