package com.example.codealpha_quoteapp.operations

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codealpha_quoteapp.operations.dataEntities.QuoteDao
import com.example.codealpha_quoteapp.operations.dataEntities.QuoteItem

@Database(entities = [QuoteItem::class], version = 1, exportSchema = false)
abstract class QuoteDatabase :RoomDatabase(){
    abstract fun quoteDao():QuoteDao
}