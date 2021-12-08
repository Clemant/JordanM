package com.capou.jordanm.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capou.jordanm.api.dao.RestaurantDAO
import com.capou.jordanm.api.model.RestaurantModel


@Database(
    entities = [
        // TODO add entity later
        RestaurantModel::class
    ],
    version = 1,
    exportSchema = false
    //retourne le schema de la base de données table ect en format json
)
abstract class MyDataBase: RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDAO
}