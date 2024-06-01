package com.erenalparslan.cookingapp.presentation.recipeDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
import com.erenalparslan.cookingapp.presentation.cook.CookListState
import com.erenalparslan.cookingapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    val cook = savedStateHandle.get<Int>("id") ?: 154

    private val _cookState = MutableStateFlow(RecipeDetailState())
    val cookState = _cookState.asStateFlow()

    init {
        getRecipesById()
    }


     fun getRecipesById() =
        viewModelScope.launch {
            recipesRepository.getRecipesById(cook).collectLatest { result ->
                when (result) {

                    is Resource.Success -> _cookState.update {
                        it.copy(
                            isLoading = false,
                            isError = false,
                            cook = result.data!!
                        )
                    }

                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }

            }
        }

}