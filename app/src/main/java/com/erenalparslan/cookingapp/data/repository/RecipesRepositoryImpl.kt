package com.erenalparslan.cookingapp.data.repository

import com.erenalparslan.cookingapp.data.remote.api.CookApi
import com.erenalparslan.cookingapp.data.remote.response.CookDto
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import com.erenalparslan.cookingapp.domain.model.Cook
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
import com.erenalparslan.cookingapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(private val cookApi: CookApi) : RecipesRepository {
    override suspend fun getRecipes(category: String): Flow<Resource<List<CookDtoItem>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.getCook()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            emit(Resource.Success(recipesListFromApi.recipesList))
        }

    }
}