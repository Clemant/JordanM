package com.capou.jordanm.architecture

import android.app.Application
import androidx.room.Room

class CustomApplication : Application() {


    companion object {
        lateinit var instance: CustomApplication
    }


    val mApplicationDatabase: MyDataBase by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDataBase::class.java,
            "JordanMDatabase"
        ).fallbackToDestructiveMigration().build()
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
