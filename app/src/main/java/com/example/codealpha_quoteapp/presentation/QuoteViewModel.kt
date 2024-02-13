package com.example.codealpha_quoteapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
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


    val historyQuotesList = repository.getAllHistoryQuotes()
    val favoritesQuotesList = repository.getAllFavoritesQuotes()


    private val _shareQuote = MutableLiveData<String>("")
    val shareQuote :LiveData<String> get() = _shareQuote

    fun setEvent(event: ViewModelEvents) {
        when (event) {
            ViewModelEvents.UpdateCurrentRandomQuoteEvent -> {
                updateCurrentRandomQuote()
            }

            is ViewModelEvents.ChangeQuoteFavoriteStatusEvent -> {
                if (event.quote !=null){
                    changeQuoteFavoriteStatus(event.quote)

                }
            }

            is ViewModelEvents.ShareQuoteContentEvent -> {
                _shareQuote.value = event.quote
                _shareQuote.value = ""
            }
        }
    }


    private fun updateCurrentRandomQuote() {
        viewModelScope.launch {
            val networkResponse: NetWorkQuoteItem = repository.getRandomQuote()[0]
            val cacheItem: CacheQuoteItem = mapper.mapNetWorkToCache(networkResponse)
            repository.insertQuote(cacheItem)
        }
    }
    private fun changeQuoteFavoriteStatus(quote: CacheQuoteItem?) {
        viewModelScope.launch {
            if (quote !=null){
                quote.favorite = !quote.favorite
                repository.updateQuote(quote)
            }
        }
    }
}





sealed class ViewModelEvents {
    data object UpdateCurrentRandomQuoteEvent : ViewModelEvents()
    data class ChangeQuoteFavoriteStatusEvent(val quote:CacheQuoteItem?=null):ViewModelEvents()

    data class ShareQuoteContentEvent(val quote:String) :ViewModelEvents()

}

