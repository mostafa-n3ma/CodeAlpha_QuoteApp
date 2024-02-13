package com.example.codealpha_quoteapp.operations.dataEntities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.google.gson.annotations.SerializedName


@Entity(tableName = "quotes")
data class CacheQuoteItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val author: String?=null,
    val content: String?=null,
    var favorite: Boolean = false,
    val length: Int?=null,
)

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): LiveData<List<CacheQuoteItem>>


    @Update
    suspend fun updateQuote(quote: CacheQuoteItem)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: CacheQuoteItem)


    @Delete
    suspend fun deleteQuote(quoteItem: CacheQuoteItem)

    @Query("DELETE  FROM quotes")
    suspend fun clearQuotesTable()


}