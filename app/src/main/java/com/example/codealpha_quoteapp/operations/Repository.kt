package com.example.codealpha_quoteapp.operations

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.codealpha_quoteapp.operations.dataEntities.QuoteDao
import com.example.codealpha_quoteapp.operations.dataEntities.CacheQuoteItem
import com.example.codealpha_quoteapp.operations.dataEntities.NetWorkQuoteItem
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val quoteDao: QuoteDao,
    private val apiService: QuoteApiService,
){

    suspend fun insertQuote(quote:CacheQuoteItem){
        quoteDao.insertQuote(quote)
    }

    suspend fun updateQuote(quote: CacheQuoteItem){
        quoteDao.updateQuote(quote)
    }

    suspend fun deleteQuote(quote: CacheQuoteItem){
        quoteDao.deleteQuote(quote)
    }

    fun getAllHistoryQuotes():LiveData<List<CacheQuoteItem>> = quoteDao.getAllQuotes()

    fun getAllFavoritesQuotes():LiveData<List<CacheQuoteItem>> = quoteDao.getAllQuotes().map { quoteItemList ->
        quoteItemList.filter {quoteItem-> quoteItem.favorite }
    }


    suspend fun getRandomQuote():List<NetWorkQuoteItem>{
        return apiService.getRandomQuote()
    }






}