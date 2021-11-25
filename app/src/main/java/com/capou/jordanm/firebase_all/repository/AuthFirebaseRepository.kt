package com.capou.jordanm.firebase_all.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

class AuthFirebaseRepository {

    private var mFirebaseAuth: FirebaseAuth = Firebase.auth
   // var mCurrentUser = MutableLiveData<FirebaseUser>()
   // var mErrorProcess = MutableLiveData<Int>()


    init {
       /* if(mFirebaseAuth.currentUser != null) {
            mCurrentUser.postValue(mFirebaseAuth.currentUser)
        } else {
            mErrorProcess.postValue(9)
        }*/
    }
    fun registerNewUser(email: String, password: String) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (mFirebaseAuth.currentUser != null) {
                        //     mCurrentUser.postValue(mFirebaseAuth.currentUser)
                        Log.d("Details",mFirebaseAuth.currentUser.toString()+" "+mFirebaseAuth.currentUser?.email.toString())
                    } else {
                        //    mErrorProcess.postValue(9)
                        Log.d("Details","Ok but we have a currrentUser")
                    }
                }
            }
            .addOnFailureListener { error ->
                Log.d("Details",error.message.toString()+" "+error.cause.toString() )
            }
    }

        fun loginUser(email: String, password: String) {
            mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Details","Lets go"+task.isSuccessful.toString()+" "+mFirebaseAuth.currentUser?.email.toString())
                        mFirebaseAuth.currentUser?.uid
                      //  mCurrentUser.postValue(mFirebaseAuth.currentUser)
                    } else {
                      //  mErrorProcess.postValue(11)
                        Log.d("Details ","Error")
                    }
                }
        }
        fun disconnectUser() {
            mFirebaseAuth.signOut()
            //mErrorProcess.postValue(5)
        }



    }