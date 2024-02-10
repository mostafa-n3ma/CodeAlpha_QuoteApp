package com.example.codealpha_quoteapp.operations.di

import com.example.codealpha_quoteapp.operations.AuthInterceptor
import com.example.codealpha_quoteapp.operations.QuoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor())
                    .build()
            )
            .build()
    }


    @Singleton
    @Provides
    fun provideQuoteApiService(retrofit: Retrofit):QuoteApiService{
        return retrofit.create(QuoteApiService::class.java)
    }

}