package com.example.codealpha_quoteapp.operations

import com.example.codealpha_quoteapp.operations.dataEntities.QuoteQuery
import retrofit2.http.GET

interface QuoteApiService {
    @GET("/quotes/random")
    suspend fun getRandomQuote():QuoteQuery
}