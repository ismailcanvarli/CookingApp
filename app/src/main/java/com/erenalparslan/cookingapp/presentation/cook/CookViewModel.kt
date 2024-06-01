package com.erenalparslan.cookingapp.presentation.cook

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.cookingapp.data.remote.response.Instruction
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
import com.erenalparslan.cookingapp.util.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CookViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipesRepository: RecipesRepository
) : ViewModel() {
    val cook = savedStateHandle.get<String>("cookName") ?: ""

    private val _cookState = MutableStateFlow(CookListState())
    val cookState = _cookState.asStateFlow()


    init {
        if (cook == "") {
            getRecipes()
        } else {
            getRecipesByCategory()
        }
    }

    private fun getRecipesByCategory() =
        viewModelScope.launch {
            recipesRepository.getRecipesByCategory(cook).collectLatest { result ->
                when (result) {
                    is Resource.Error -> _cookState.update {
                        it.copy(
                            isError = true,
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> _cookState.update {
                        it.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is Resource.Success -> _cookState.update {
                        it.copy(
                            isLoading = false,
                            isError = false,
                            cookList = result.data!!
                        )
                    }
                }

            }
        }

    private fun getRecipes() =
        viewModelScope.launch {
            recipesRepository.getRecipes().collectLatest { result ->
                when (result) {
                    is Resource.Error -> _cookState.update {
                        it.copy(
                            isError = true,
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> _cookState.update {
                        it.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is Resource.Success -> _cookState.update {
                        it.copy(
                            isLoading = false,
                            isError = false,
                            cookList = result.data!!
                        )
                    }
                }

            }
        }

    fun getRecipesByFoodName(foodName: String) =
        viewModelScope.launch {
            if (foodName == "") {
                getRecipes()
            } else {
                recipesRepository.getRecipesByName(foodName).collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> _cookState.update {
                            it.copy(
                                isLoading = true,
                                isError = false
                            )
                        }

                        is Resource.Success -> _cookState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                cookList = result.data!!
                            )
                        }

                        is Resource.Error -> {}
                    }

                }
            }

        }


}