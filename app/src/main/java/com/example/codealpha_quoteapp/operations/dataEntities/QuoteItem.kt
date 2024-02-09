package com.example.codealpha_quoteapp.operations.dataEntities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query


@Entity(tableName = "quotes")
data class QuoteItem(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val author: String,
    val content: String,
    val favorite: Boolean,
    val dateModified: String,
    val length: Int,
)

@Dao
interface QuoteDao{
    @Query("SELECT * FROM quotes")
    fun getAllQuotes():LiveData<List<QuoteItem>>
}