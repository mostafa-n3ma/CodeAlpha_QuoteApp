package com.example.codealpha_quoteapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codealpha_quoteapp.operations.Repository
import com.example.codealpha_quoteapp.operations.dataEntities.CacheQuoteItem
import com.example.codealpha_quoteapp.operations.dataEntities.EntitiesMapper
import com.example.codealpha_quoteapp.operations.dataEntities.NetWorkQuoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel
@Inject
constructor(
    private val repository: Repository,
    private val mapper: EntitiesMapper
) : ViewModel() {
    companion object {
        const val TAG = "QuoteViewModel"
    }

    private val _currentRandomQuote = MutableStateFlow<CacheQuoteItem?>(null)
    val currentRandomQuote: StateFlow<CacheQuoteItem?> = _currentRandomQuote

    val historyQuotesList = repository.getAllHistoryQuotes()
    val favoritesQuotesList = repository.getAllFavoritesQuotes()

    fun setEvent(events: ViewModelEvents) {
        when (events) {
            ViewModelEvents.UpdateCurrenRandomQuote -> {
                updateCurrentRandomQuote()
            }
        }
    }


    private fun updateCurrentRandomQuote() {
        viewModelScope.launch {
            val networkResponse: NetWorkQuoteItem = repository.getRandomQuote()[0]
            val cacheItem: CacheQuoteItem = mapper.mapNetWorkToCache(networkResponse)
            _currentRandomQuote.update { cacheItem }
            repository.insertQuote(cacheItem)
        }
    }

}


sealed class ViewModelEvents {
    object UpdateCurrenRandomQuote : ViewModelEvents()
}