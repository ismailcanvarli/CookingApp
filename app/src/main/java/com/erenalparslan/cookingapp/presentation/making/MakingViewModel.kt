package com.erenalparslan.cookingapp.presentation.making

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.cookingapp.data.remote.response.Instruction
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
import com.erenalparslan.cookingapp.presentation.cook.CookListState
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
import kotlin.math.log

@HiltViewModel
class MakingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipesRepository: RecipesRepository
) : ViewModel() {
    private val cook = savedStateHandle.get<Int>("id") ?: 154
    private val _cookState = MutableStateFlow(MakingStepState())
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
                            list = result.data?.instructions!!
                        )
                    }

                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }

            }
        }

    fun askGemini(ask: String?) =
        viewModelScope.launch {
            recipesRepository.askGemini(ask?:"").collectLatest { result ->
            when (result) {

                is Resource.Success -> _cookState.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        question = result.data
                    )
                }

                is Resource.Error -> {}
                is Resource.Loading -> {}
            }

        }
        }


}