package com.capou.jordanm.api.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.capou.jordanm.api.model.RestaurantModel


@Dao
interface RestaurantDAO {

    @Query("SELECT * FROM restaurant")
    fun getAllRestaurant():LiveData<List<RestaurantModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurantModel: RestaurantModel)

    @Query("DELETE FROM restaurant")
    suspend fun deleteAll()

}