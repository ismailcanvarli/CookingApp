package com.erenalparslan.cookingapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun putString(key: String, value: String)

    fun getString(key: String): Flow<String?>

    suspend fun removeKey(key: String)

    suspend fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String): Flow<Boolean?>

    suspend fun removeBooleanKey(key: String)
}