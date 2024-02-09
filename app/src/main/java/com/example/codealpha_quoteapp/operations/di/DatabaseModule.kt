package com.example.codealpha_quoteapp.operations.di

import android.content.Context
import androidx.room.Room
import com.example.codealpha_quoteapp.operations.QuoteDatabase
import com.example.codealpha_quoteapp.operations.dataEntities.QuoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):QuoteDatabase{
        return Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "QuoteDatabase"
        ).fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun provideQuoteDao(database: QuoteDatabase):QuoteDao{
        return database.quoteDao()
    }




}