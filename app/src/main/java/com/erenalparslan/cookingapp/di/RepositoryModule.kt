package com.erenalparslan.cookingapp.di

import com.erenalparslan.cookingapp.data.remote.api.CookApi
import com.erenalparslan.cookingapp.data.repository.RecipesRepositoryImpl
import com.erenalparslan.cookingapp.domain.repository.RecipesRepository
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
}