package com.erenalparslan.cookingapp.domain.repository

import com.erenalparslan.cookingapp.data.remote.response.CookDto
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import com.erenalparslan.cookingapp.domain.model.Cook
import com.erenalparslan.cookingapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun getRecipes(category: String):Flow<Resource<List<CookDtoItem>>>
}