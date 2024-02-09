package com.example.codealpha_quoteapp.operations.di

import com.example.codealpha_quoteapp.operations.QuoteApiService
import com.example.codealpha_quoteapp.operations.QuoteDatabase
import com.example.codealpha_quoteapp.operations.Repository
import com.example.codealpha_quoteapp.operations.dataEntities.QuoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(quoteDao: QuoteDao,apiService: QuoteApiService):Repository{
        return Repository(quoteDao,apiService)
    }
}