package com.capou.jordanm.api.remote

import com.capou.jordanm.api.model.RestaurantModelAPI
import retrofit2.http.GET

interface RestaurantEndPoint {
    @GET("random_restaurant")
    suspend fun getRandomRestaurant() : RestaurantModelAPI
}