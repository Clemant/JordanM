package com.capou.jordanm.firebase_all.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capou.jordanm.firebase_all.repository.AuthFirebaseRepository
import com.google.firebase.auth.FirebaseUser

class AuthFirebaseViewModel: ViewModel() {
    private val mFirebaseAuthRepository: AuthFirebaseRepository by lazy { AuthFirebaseRepository() }
    // var mCurrentUser = MutableLiveData<FirebaseUser>()
    // var mErrorProcess = MutableLiveData<Int>()
    var test: MutableLiveData<String> = MutableLiveData<String>()
    //var listError :MutableLiveData<String> = MutableLiveData<String>()

    private val data = MutableLiveData<String>().apply {
     value
    }

    val defaultdata : LiveData<String> = data

    init {
        // mCurrentUser = mFirebaseAuthRepository.mCurrentUser
        //   mErrorProcess = mFirebaseAuthRepository.mErrorProcess
        test = AuthFirebaseRepository().default
       // listError = AuthFirebaseRepository().listErrors
    }


    fun loginUser(email: String, password: String) {
        mFirebaseAuthRepository.loginUser(email, password)

     /*   test.observeForever({
            Log.d("Data",it.toString())
        } )

        Log.d("Data","--->"+ test.hasObservers()+" "+ test.value)
*/

    }

    fun getUser():MutableLiveData<String>{
        return mFirebaseAuthRepository.getCurrentUid();
    }

    fun registerNewUser(email: String, password: String) {
        mFirebaseAuthRepository.registerNewUser(email, password)
    }

    fun getMessageListener():MutableLiveData<Map<String,String>>{
        return mFirebaseAuthRepository.getMessageListener();
    }


    fun disconnectUser() {
        mFirebaseAuthRepository.disconnectUser()
    }
}