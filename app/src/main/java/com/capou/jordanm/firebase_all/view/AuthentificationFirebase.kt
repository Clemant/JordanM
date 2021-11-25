package com.capou.jordanm.firebase_all.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capou.jordanm.R
import com.capou.jordanm.databinding.ActivityAuthentificationFirebaseBinding
import com.capou.jordanm.firebase_all.viewModel.AuthFirebaseViewModel
import java.util.*

class AuthentificationFirebase: AppCompatActivity() {
    private lateinit var mViewModel: AuthFirebaseViewModel
    private lateinit var binding: ActivityAuthentificationFirebaseBinding

/*
    private var mObserverUser = Observer<FirebaseUser> {
        updateUser(it)
    }


    private var mObserverError = Observer<Int> {
        updateError(it)
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_authentification_firebase)
        binding = ActivityAuthentificationFirebaseBinding.inflate(layoutInflater);

        mViewModel = ViewModelProvider(this)[AuthFirebaseViewModel::class.java]
       // firebaseButtonRegister.setOnClickListener { register() }
        binding.signIn.setOnClickListener { login() }
        binding.signUp.setOnClickListener {
            val intent = Intent(applicationContext,SignUpFirebase::class.java)
            startActivity(intent)
        }
      //  firebaseButtonDisconnect.setOnClickListener { disconnect() }

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
    /*    mViewModel.mCurrentUser.observe(this, mObserverUser)
        mViewModel.mErrorProcess.observe(this, mObserverError)*/
    }


    override fun onStop() {
    /*    mViewModel.mCurrentUser.removeObserver(mObserverUser)
        mViewModel.mErrorProcess.removeObserver(mObserverError)*/
        super.onStop()
    }

    private fun login() {
       // if (checkConformityFields()) {
           // mViewModel.loginUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
            mViewModel.loginUser("test@test.com","Bonjour")
      //  }
    }

/*
    private fun register() {
        if (checkConformityFields()) {
         //   mViewModel.registerNewUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
        }
    }
    private fun disconnect() {
        if (checkConformityFields()) {
            mViewModel.disconnectUser()
        }
    }*/


/*
    private fun updateUser(user : FirebaseUser) {
        user.let {
            firebaseLog.text = "${user.uid}-${user.email}"
        }
    }*/


}