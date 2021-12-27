package com.capou.jordanm.api.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.capou.jordanm.api.model.ChuckNorrisRetrofit
import com.capou.jordanm.api.model.ChuckNorrisRoom
import com.capou.jordanm.architecture.CustomApplication
import com.capou.jordanm.architecture.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ChuckNorrisQuoteRepository {
    private val mChuckNorrisDao = CustomApplication.instance.mApplicationDatabase.chuckNorrisDao()

    fun selectAllChuckNorrisQuote(): LiveData<List<ChuckNorrisRoom>> {
        return mChuckNorrisDao.selectAll()
    }


    private suspend fun insertChuckNorrisQuote(chuckNorrisQuote: ChuckNorrisRoom) = withContext(Dispatchers.IO){
            mChuckNorrisDao.insert(chuckNorrisQuote)
        }


    suspend fun deleteAllChuckNorrisQuote() = withContext(Dispatchers.IO) {
        mChuckNorrisDao.deleteAll()
    }

    suspend fun deleteById(phone_number:String)=
        withContext(Dispatchers.IO){
            mChuckNorrisDao.deleteById(phone_number)
        }

   suspend fun fetchData() {

        val result = RetrofitBuilder.getChuckNorrisQuote().getRandomQuote().toRoom()
        Log.d("Details",result.toString())
        insertChuckNorrisQuote(result)
    }
}


private fun ChuckNorrisRetrofit.toRoom(): ChuckNorrisRoom {
    return ChuckNorrisRoom(
        name = name,
        type = type,
        description = description,
        logo = logo,
        address= address,
        phone_number= phone_number,
        date = System.currentTimeMillis()
    )
}
