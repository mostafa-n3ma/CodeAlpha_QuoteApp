package com.example.codealpha_quoteapp.operations.dataEntities

import javax.inject.Inject

class EntitiesMapper
@Inject
constructor(){
    fun mapNetWorkToCache(netWorkQuoteItem: NetWorkQuoteItem):CacheQuoteItem{
        return CacheQuoteItem(
            id = null,
            author = netWorkQuoteItem.author,
            content = netWorkQuoteItem.quote,
            length = netWorkQuoteItem.quote.length
        )
    }

    fun mapNetworkListToCache(list: List<NetWorkQuoteItem>):List<CacheQuoteItem>{
        return list.map {
            mapNetWorkToCache(it)
        }
    }

}