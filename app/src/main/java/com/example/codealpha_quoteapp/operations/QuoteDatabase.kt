package com.example.codealpha_quoteapp.operations

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codealpha_quoteapp.operations.dataEntities.QuoteDao
import com.example.codealpha_quoteapp.operations.dataEntities.CacheQuoteItem

@Database(entities = [CacheQuoteItem::class], version = 1, exportSchema = false)
abstract class QuoteDatabase :RoomDatabase(){
    abstract fun quoteDao():QuoteDao
}