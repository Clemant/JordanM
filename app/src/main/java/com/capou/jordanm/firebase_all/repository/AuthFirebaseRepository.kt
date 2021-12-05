package com.capou.jordanm.firebase_all.repository

import android.app.AlertDialog
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth

class AuthFirebaseRepository {

    private var mFirebaseAuth: FirebaseAuth = Firebase.auth
    var default = MutableLiveData<String>()
    private var infoListener = MutableLiveData<Map<String,String>>()
    private var mapList = emptyMap<String,String>()
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
      val info =  mFirebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (mFirebaseAuth.currentUser != null) {
                        //     mCurrentUser.postValue(mFirebaseAuth.currentUser
                        mapList = mapOf("success_message" to "Your account has been created. ")
                        infoListener.postValue(mapList)
                        Log.d("Details",mFirebaseAuth.currentUser.toString()+" "+mFirebaseAuth.currentUser?.email.toString()+" "+mFirebaseAuth.currentUser?.uid.toString())
                        Log.d("Details",task.result.toString())
                        mFirebaseAuth.signOut()
                    }
                }
            }
            .addOnFailureListener { error ->
                mapList = mapOf("errors_message" to error.message.toString())
                infoListener.postValue(mapList)
                Log.d("Details",error.message.toString()+" "+error.cause.toString() )
                }


        Log.d("Details","default"+ info.toString())

    }

        fun loginUser(email: String, password: String) {
            mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Details","Lets go"+task.isSuccessful.toString()+" "+mFirebaseAuth.currentUser?.email.toString())
                        Log.d("Details",mFirebaseAuth.currentUser?.uid.toString())
                        mapList = mapOf("success_message" to "Successfull login. ")
                        infoListener.postValue(mapList)
                        default.postValue(mFirebaseAuth.currentUser?.uid.toString())
                      //  mCurrentUser.postValue(mFirebaseAuth.currentUser)
                    }
                }
                .addOnFailureListener { error ->
                    mapList = mapOf("errors_message" to error.message.toString())
                    infoListener.postValue(mapList)
                    Log.d("Details",error.message.toString()+" "+error.cause.toString() )
                }
        }


        fun disconnectUser() {
            mFirebaseAuth.signOut()
            Log.d("TAG",mFirebaseAuth.currentUser?.uid.toString()+" "+mFirebaseAuth.currentUser?.email.toString())

        }

    fun getCurrentUid():MutableLiveData<String>{
        return default
    }

    fun getMessageListener():MutableLiveData<Map<String,String>>{
        return infoListener
    }



    }