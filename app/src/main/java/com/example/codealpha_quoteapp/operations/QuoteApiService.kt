package com.example.codealpha_quoteapp.operations

import com.example.codealpha_quoteapp.operations.dataEntities.NetWorkQuoteItem
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApiService {
//    @GET("quotes/random")
//    suspend fun getRandomQuote():QuoteQuery


    @GET("/v1/quotes")
    suspend fun getRandomQuote(): List<NetWorkQuoteItem>
}



class AuthInterceptor():Interceptor{
    val apiKey = "ip6KcK38+GxDrIeE3iNrQw==gxaruOM0Pd4gkuCz"
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("X-Api-Key", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}