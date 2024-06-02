package com.erenalparslan.cookingapp.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.erenalparslan.cookingapp.domain.repository.DataStoreRepository
import com.erenalparslan.cookingapp.util.Constants.USER_PREFERENCES
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES
)

class DataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : DataStoreRepository {
    override suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override fun getString(key: String): Flow<String?> = flow {
        try {
            val preferencesKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            emit(preferences[preferencesKey])
        } catch (e: Exception) {
            Log.d(TAG, "getString: $e")
        }
    }

    override suspend fun removeKey(key: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override fun getBoolean(key: String): Flow<Boolean?> = flow {
        try {
            val preferencesKey = booleanPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            emit(preferences[preferencesKey])
        } catch (e: Exception) {
            Log.d(TAG, "getString: $e")
        }
    }

    override suspend fun removeBooleanKey(key: String) {
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
        }
    }

    companion object {
        const val TAG = "DataStoreRepositoryImpl"
    }
}