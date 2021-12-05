package com.capou.jordanm.firebase_all.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capou.jordanm.R
import com.capou.jordanm.databinding.ActivityAuthentificationFirebaseBinding
import com.capou.jordanm.firebase_all.viewModel.AuthFirebaseViewModel
import java.util.*

class AuthentificationFirebase: AppCompatActivity() {
    private lateinit var mViewModel: AuthFirebaseViewModel
    private lateinit var binding: ActivityAuthentificationFirebaseBinding

    companion object{
        var isDeconnected:Boolean = true
    }


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
        binding.signIn.setOnClickListener {

            if(isDeconnected){
                login()
            }
            else{
                disconnect()
            }
           // isDeconnected  =!isDeconnected
            Log.d("TAG", isDeconnected.toString())

        }
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
        mViewModel.test.observeForever({
            Log.d("Login",it.toString());
        })
    }


    override fun onStop() {
    /*    mViewModel.mCurrentUser.removeObserver(mObserverUser)
        mViewModel.mErrorProcess.removeObserver(mObserverError)*/
        super.onStop()
    }

    private fun login() {
       // if (checkConformityFields()) {
           // mViewModel.loginUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
        var email    =  binding.authEmail.text.toString()
        var password = binding.authPassword.text.toString()

        mViewModel.loginUser(email,password)
      //       mViewModel.loginUser("test@test.com","Bonjour")
      // Pensez a mettre une verif sur l'email et le mot de passe...
        mViewModel.test.observe(this,
            {
               Log.d("Login",it.toString())
            })
        mViewModel?.defaultdata.observe(this,{
            Log.d("Login",it.toString())
        })
        mViewModel.getUser().observe(this,{
            Log.d("TAG",it)
            if(it !=null && TextUtils.isEmpty(it) == false){
                isDeconnected = false
                binding.signIn.text = getString(R.string.signout)
                binding.authEmail.visibility = View.GONE
                binding.authPassword.visibility = View.GONE
                binding.authUID.visibility = View.VISIBLE
                binding.authUID.text = getString(R.string.uid)+" "+it
                binding.signUp.visibility = View.GONE
            }
        })
        mViewModel.getMessageListener().observe(this,{
            var test: String?
            if(it.containsKey("success_message")){
                test = it.get("success_message").toString()

            }
            else{
                test = it.get("errors_message") .toString()
            }
            Toast.makeText(applicationContext,test,Toast.LENGTH_LONG).show()

        })

    }

/*
    private fun register() {
        if (checkConformityFields()) {
         //   mViewModel.registerNewUser(firebaseUserEmail.text.toString(), firebaseUserPassword.text.toString())
        }
    }*/
    private fun disconnect() {
        mViewModel.disconnectUser()
        binding.authEmail.visibility = View.VISIBLE
        binding.authPassword.visibility = View.VISIBLE
        isDeconnected = true
        binding.authEmail.text = null
        binding.authPassword.text = null
        binding.authUID.visibility = View.GONE
        binding.signIn.text = getString(R.string.connexion)
        binding.signUp.visibility = View.VISIBLE
        Log.d("TAG",binding.authUID.text.toString())
        }



/*
    private fun updateUser(user : FirebaseUser) {
        user.let {
            firebaseLog.text = "${user.uid}-${user.email}"
        }
    }*/


}