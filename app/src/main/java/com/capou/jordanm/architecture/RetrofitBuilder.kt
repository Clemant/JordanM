package com.capou.jordanm.architecture

import com.capou.jordanm.api.remote.RestaurantEndPoint
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


    object RetrofitBuilder {

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://random-data-api.com/api/restaurant/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
            .build()

        fun getRestaurantApi(): RestaurantEndPoint = retrofit.create(RestaurantEndPoint::class.java)
    }
