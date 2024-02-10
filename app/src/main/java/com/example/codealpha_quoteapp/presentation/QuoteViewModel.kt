package com.example.codealpha_quoteapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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

    fun setEvent(event: ViewModelEvents) {
        when (event) {
            ViewModelEvents.UpdateCurrentRandomQuote -> {
                updateCurrentRandomQuote()
            }

            is ViewModelEvents.MakeQuoteFavorite -> {
                if (event.quote !=null){
                    makeQuoteFavorite(event.quote)
                }
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
    fun makeQuoteFavorite(quote: CacheQuoteItem) {
        viewModelScope.launch {
            quote.favorite = true
            repository.updateQuote(quote)
        }
    }
}





sealed class ViewModelEvents {
    data object UpdateCurrentRandomQuote : ViewModelEvents()
    data class MakeQuoteFavorite(val quote:CacheQuoteItem?=null):ViewModelEvents()

}

