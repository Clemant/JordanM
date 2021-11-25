package com.capou.jordanm.firebase_all.viewModel

import androidx.lifecycle.ViewModel
import com.capou.jordanm.firebase_all.repository.AuthFirebaseRepository

class AuthFirebaseViewModel: ViewModel() {
    private val mFirebaseAuthRepository: AuthFirebaseRepository by lazy { AuthFirebaseRepository() }
    // var mCurrentUser = MutableLiveData<FirebaseUser>()
    // var mErrorProcess = MutableLiveData<Int>()


    init {
        // mCurrentUser = mFirebaseAuthRepository.mCurrentUser
        //   mErrorProcess = mFirebaseAuthRepository.mErrorProcess
    }


    fun loginUser(email: String, password: String) {
        mFirebaseAuthRepository.loginUser(email, password)
    }


    fun registerNewUser(email: String, password: String) {
        mFirebaseAuthRepository.registerNewUser(email, password)
    }


    fun disconnectUser() {
        mFirebaseAuthRepository.disconnectUser()
    }
}