package com.erenalparslan.cookingapp.presentation.favorites

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.cookingapp.MainActivityViewModel.Companion.TOKEN
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
class FavoritesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipesRepository: RecipesRepository
) : ViewModel() {


    init {
        getFavorites()
    }

    private val _cookState = MutableStateFlow(FavoritesState())
    val cookState = _cookState.asStateFlow()

    private fun getFavorites() {
        viewModelScope.launch {
            recipesRepository.getLikedRecipes(TOKEN).collectLatest { result ->
                when (result) {

                    is Resource.Success -> _cookState.update {
                        it.copy(
                            isLoading = false,
                            isError = false,
                            cookList = result.data!!
                        )
                    }

                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }

            }
        }
    }
}