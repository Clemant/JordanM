package com.capou.jordanm.api.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.capou.jordanm.api.model.ChuckNorrisRoom
import com.capou.jordanm.api.model.ChuckNorrisUi
import com.capou.jordanm.api.model.MyObjectForRecyclerView
import com.capou.jordanm.api.model.RestaurantHeader
import com.capou.jordanm.api.repository.ChuckNorrisQuoteRepository
import com.capou.jordanm.architecture.service.MyCloudMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class ChuckNorrisViewModel : ViewModel() {


    private val mChuckNorrisQuoteRepository: ChuckNorrisQuoteRepository by lazy { ChuckNorrisQuoteRepository() }
    private val MyCloudMessaging:MyCloudMessaging by lazy {MyCloudMessaging()}
   /*var mChuckNorrisQuoteLiveData: LiveData<List<MyObjectForRecyclerView>> =
        mChuckNorrisQuoteRepository.selectAllChuckNorrisQuote().map {
            it.toUi2()
        }*/

    var mChuckNorrisQuoteLiveData:LiveData<List<MyObjectForRecyclerView>> =
        mChuckNorrisQuoteRepository.selectAllChuckNorrisQuote().map {
            it.toUi2()
        }
    init {


    }

    fun fetchNewQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            mChuckNorrisQuoteRepository.fetchData()
        }
    }




    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            mChuckNorrisQuoteRepository.deleteAllChuckNorrisQuote()
        }
    }

    fun deleteById(phone_number:String){
        viewModelScope.launch(Dispatchers.IO){
            mChuckNorrisQuoteRepository.deleteById(phone_number)
        }
    }
}


private fun List<ChuckNorrisRoom>.toUi(): List<ChuckNorrisUi> {
    return asSequence().map {
        ChuckNorrisUi(
            name = it.name,
            type = it.type,
            description = it.description,
            logo = it.logo,
            address= it.address,
            phone_number= it.phone_number,
            date = it.date
        )
    }.toList()
}

private fun List<ChuckNorrisRoom>.toUi2(): List<MyObjectForRecyclerView>{
    var result = mutableListOf<MyObjectForRecyclerView>()
    asSequence().map {
        ChuckNorrisUi(
            name = it.name,
            type = it.type,
            description = it.description,
            logo = it.logo,
            address= it.address,
            phone_number= it.phone_number,
            date = it.date
        )
    }.groupBy {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it.date)
    }.forEach{  (isModulo, items) ->
        result.add(RestaurantHeader("${isModulo}"))
        result.addAll(items)
    }
        return result

}
