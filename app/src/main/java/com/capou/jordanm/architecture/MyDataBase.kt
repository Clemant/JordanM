package com.capou.jordanm.architecture

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capou.jordanm.api.dao.ChuckNorrisDao
import com.capou.jordanm.api.model.ChuckNorrisRoom

@Database(
    entities = [
        ChuckNorrisRoom::class
    ],
    version = 3,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {


    abstract fun chuckNorrisDao() : ChuckNorrisDao

}
