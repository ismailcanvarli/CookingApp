package com.erenalparslan.cookingapp.presentation.cook

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CookViewModel@Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

     val cook = savedStateHandle.get<String?>("cookName")
    private val _cookState= MutableStateFlow(CookListState())
     val cookState= _cookState.asStateFlow()



}