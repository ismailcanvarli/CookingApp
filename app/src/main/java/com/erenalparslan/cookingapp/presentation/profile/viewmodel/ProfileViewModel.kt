package com.erenalparslan.cookingapp.presentation.profile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.cookingapp.MainActivityViewModel.Companion.TOKEN
import com.erenalparslan.cookingapp.data.remote.response.LoginDto
import com.erenalparslan.cookingapp.data.remote.response.RegisterDto
import com.erenalparslan.cookingapp.domain.repository.DataStoreRepository
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
import com.erenalparslan.cookingapp.presentation.profile.state.ProfileState
import com.erenalparslan.cookingapp.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow(ProfileState())
    val registerState = _registerState.asStateFlow()


    init {
        checkLoginStatus()
        getUser()
    }

    fun register(member: RegisterDto) {
        viewModelScope.launch {
            val jsonMember = Gson().toJson(member)
            recipesRepository.register(member).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {}

                    is Resource.Loading -> {}


                    is Resource.Success ->
                        _registerState.update {
                            it.copy(
                                isSuccess = true,
                                isError = false,
                                isLoading = false
                            )
                        }
                }

            }

        }

    }


    fun login(member: LoginDto) {
        viewModelScope.launch {
            recipesRepository.login(member).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {}

                    is Resource.Loading -> {
                        _registerState.update {
                            it.copy(
                                isSuccess = false,
                                isError = false,
                                isLoading = true,
                                isLogin = false
                            )
                        }
                    }

                    is Resource.Success -> {
                        _registerState.update {
                            it.copy(
                                isSuccess = true,
                                isError = false,
                                isLoading = false,
                                isLogin = true
                            )
                        }
                        dataStoreRepository.putBoolean("hasUser", true)
                        TOKEN = result.data?.token ?: ""
                        dataStoreRepository.putString("token", result.data?.token ?: "")
                    }

                }

            }

        }

    }

    fun getUser() {
        viewModelScope.launch {
            recipesRepository.getProfile(TOKEN).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {}

                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        _registerState.update {
                            it.copy(
                                isSuccess = true,
                                isError = false,
                                isLoading = false,
                                profile = result.data
                            )
                        }
                    }

                }

            }

        }

    }


    private fun checkLoginStatus() = viewModelScope.launch {
        dataStoreRepository.getBoolean("hasUser").collect { result ->
            result?.let { isPaired ->
                _registerState.update {
                    it.copy(
                        isLogin = isPaired
                    )
                }
            }
        }
    }

    fun logOut() = viewModelScope.launch {
        dataStoreRepository.putBoolean("hasUser", false)
        _registerState.update {
            it.copy(
                isLogin = false
            )
        }
    }


}

