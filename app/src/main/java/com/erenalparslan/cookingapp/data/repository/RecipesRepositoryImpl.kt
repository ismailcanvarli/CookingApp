package com.erenalparslan.cookingapp.data.repository

import com.erenalparslan.cookingapp.data.remote.api.CookApi
import com.erenalparslan.cookingapp.data.remote.response.CookDtoItem
import com.erenalparslan.cookingapp.data.remote.response.GetProfileResponse
import com.erenalparslan.cookingapp.data.remote.response.LoginDto
import com.erenalparslan.cookingapp.data.remote.response.LoginResponse
import com.erenalparslan.cookingapp.data.remote.response.RegisterDto
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
import com.erenalparslan.cookingapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(private val cookApi: CookApi) : RecipesRepository {
    override suspend fun getRecipesByCategory(category: String): Flow<Resource<List<CookDtoItem>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.getRecipeByCategory(category)
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

            emit(Resource.Success(recipesListFromApi))
        }

    }

    override suspend fun getRecipes(): Flow<Resource<List<CookDtoItem>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.getRecipe()
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

            emit(Resource.Success(recipesListFromApi))
        }
    }

    override suspend fun getRecipesByName(foodName: String): Flow<Resource<List<CookDtoItem>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.searchRecipes(foodName = foodName)
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

            emit(Resource.Success(recipesListFromApi))
        }
    }

    override suspend fun getRecipesById(id: Int): Flow<Resource<CookDtoItem>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.getRecipeById(id)
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

            emit(Resource.Success(recipesListFromApi))
        }
    }

    override suspend fun register(member: RegisterDto): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.register(member).toString()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Success(""))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Success("Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Success("Error loading movies"))
                return@flow
            }

            emit(Resource.Success(recipesListFromApi))

        }
    }

    override suspend fun login(member: LoginDto): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.login(member)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            }

            emit(Resource.Success(recipesListFromApi))

        }
    }

    override suspend fun askGemini(question: String): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.askGemini(question)

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            }

            emit(Resource.Success(recipesListFromApi.explanation))

        }
    }

    override suspend fun getProfile(token: String): Flow<Resource<GetProfileResponse>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.getProfile("Bearer $token")

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            }

            emit(Resource.Success(recipesListFromApi))

        }
    }

    override suspend fun getLikedRecipes(token: String): Flow<Resource<List<CookDtoItem>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val recipesListFromApi = try {
                cookApi.getLikedRecipes("Bearer $token")

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = e.printStackTrace().toString()))
                return@flow
            }

            emit(Resource.Success(recipesListFromApi))

        }
    }
}