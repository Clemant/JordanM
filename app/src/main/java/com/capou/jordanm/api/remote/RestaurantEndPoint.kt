package com.capou.jordanm.api.remote

import com.capou.jordanm.api.model.ChuckNorrisRetrofit
import retrofit2.http.GET


interface ChuckNorrisQuoteEndpoint {
    @GET("random_restaurant")
    suspend fun getRandomQuote() : ChuckNorrisRetrofit
}
