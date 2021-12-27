package com.capou.jordanm.api.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capou.jordanm.api.model.ChuckNorrisRoom

@Dao
interface ChuckNorrisDao {

    @Query("SELECT * FROM restaurant")
    fun selectAll() : LiveData<List<ChuckNorrisRoom>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chuckNorrisRoom: ChuckNorrisRoom)


    @Query("DELETE FROM restaurant")
    fun deleteAll()

    @Query("DELETE FROM restaurant WHERE phone_number =:number")
    fun deleteById(number:String)
}
