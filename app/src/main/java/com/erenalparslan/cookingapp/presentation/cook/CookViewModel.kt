package com.erenalparslan.cookingapp.presentation.cook

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.cookingapp.data.remote.api.CookApi
import com.erenalparslan.cookingapp.data.repository.RecipesRepositoryImpl
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
import com.erenalparslan.cookingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
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
        getRecipe()
    }

    private fun getRecipe() =
        viewModelScope.launch {
            recipesRepository.getRecipes(cook).collectLatest { result ->
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
                            cookList = result.data!!
                        )
                    }
                }

            }
        }


}