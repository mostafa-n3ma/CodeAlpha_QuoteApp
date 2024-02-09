package com.example.codealpha_quoteapp.operations

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.codealpha_quoteapp.operations.dataEntities.QuoteDao
import com.example.codealpha_quoteapp.operations.dataEntities.QuoteItem
import javax.inject.Inject

class Repository
@Inject
constructor(
    private val quoteDao: QuoteDao,
    private val apiService: QuoteApiService
){

    fun getAllHistoryQuotes():LiveData<List<QuoteItem>> = quoteDao.getAllQuotes()

    fun getAllFavoritesQuotes():LiveData<List<QuoteItem>> = quoteDao.getAllQuotes().map { quoteItemList ->
        quoteItemList.filter {quoteItem-> quoteItem.favorite }
    }




}