package com.capou.jordanm.firebase_all.view

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capou.jordanm.databinding.ActivityAuthentificationFirebaseBinding
import com.capou.jordanm.databinding.ActivitySignUpFirebaseBinding
import com.capou.jordanm.firebase_all.viewModel.AuthFirebaseViewModel

class SignUpFirebase : AppCompatActivity() {
    private lateinit var mViewModel: AuthFirebaseViewModel
    private lateinit var binding: ActivitySignUpFirebaseBinding

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
        binding = ActivitySignUpFirebaseBinding.inflate(layoutInflater);

        mViewModel = ViewModelProvider(this)[AuthFirebaseViewModel::class.java]
        // firebaseButtonRegister.setOnClickListener { register() }
        binding.signUp.setOnClickListener { register() }
        //  firebaseButtonDisconnect.setOnClickListener { disconnect() }

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        /*    mViewModel.mCurrentUser.observe(this, mObserverUser)
            mViewModel.mErrorProcess.observe(this, mObserverError)*/
    }

    private fun checkConformityFields(): Boolean {
        var isValid = true

         if (TextUtils.isEmpty(binding.authPassword.toString()) || TextUtils.isEmpty(binding.authPasswordConfirm.toString())) {
               isValid = false
           }

        return isValid
    }



    private fun register() {
        if (checkConformityFields()) {
              mViewModel.registerNewUser("bonjour@test.com", "Default_test")
        }
    }
}