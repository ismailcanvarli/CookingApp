package com.erenalparslan.cookingapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.cookingapp.domain.repository.DataStoreRepository
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
import com.erenalparslan.cookingapp.presentation.profile.viewmodel.ProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    init {
        checkTokenStatus()

    }

    private fun checkTokenStatus() = viewModelScope.launch {
        dataStoreRepository.getString("token").collect { result ->
            Log.d("Erenss", "checkTokenStatus:  $result")
            result?.let { token ->
                TOKEN = token
            }
        }
    }

    companion object {

        var TOKEN = ""
    }
}