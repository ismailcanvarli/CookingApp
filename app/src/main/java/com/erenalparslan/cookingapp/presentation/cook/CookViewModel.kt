package com.erenalparslan.cookingapp.presentation.cook

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.erenalparslan.cookingapp.data.remote.api.CookApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CookViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val api: CookApi
) : ViewModel() {


    val cook = savedStateHandle.get<String?>("cookName")

    private val _cookState = MutableStateFlow(CookListState())
    val cookState = _cookState.asStateFlow()

    suspend fun getRecipe() =
        flow {
            val cooks = api.getCook()
            emit(cooks)
        }


}