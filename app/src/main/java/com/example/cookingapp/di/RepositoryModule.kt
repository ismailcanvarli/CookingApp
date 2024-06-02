package com.example.cookingapp.di

import com.example.cookingapp.data.repository.DataStoreRepositoryImpl
import com.example.cookingapp.data.repository.RecipesRepositoryImpl
import com.example.cookingapp.domain.repository.DataStoreRepository
import com.example.cookingapp.domain.repository.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecipesRepository(recipesRepositoryImpl: RecipesRepositoryImpl): RecipesRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository
}