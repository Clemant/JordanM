package com.capou.jordanm.firebase_all.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capou.jordanm.databinding.ActivityAuthentificationFirebaseBinding
import com.capou.jordanm.databinding.ActivitySignUpFirebaseBinding
import com.capou.jordanm.firebase_all.viewModel.AuthFirebaseViewModel
import com.google.firebase.auth.FirebaseUser
import java.util.regex.Pattern

class SignUpFirebase : AppCompatActivity() {
    private lateinit var mViewModel: AuthFirebaseViewModel
    private lateinit var binding: ActivitySignUpFirebaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_authentification_firebase)
        binding = ActivitySignUpFirebaseBinding.inflate(layoutInflater);

        mViewModel = ViewModelProvider(this)[AuthFirebaseViewModel::class.java]


        // firebaseButtonRegister.setOnClickListener { register() }

        //  firebaseButtonDisconnect.setOnClickListener { disconnect() }

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.signIn.setOnClickListener {
            val intent = Intent(applicationContext,AuthentificationFirebase::class.java)
            startActivity(intent)
        }

        binding.signUp.setOnClickListener { register() }
    }

    private fun checkConformityFields(password:String): Boolean {
        var isValid = true

        val pattern = Pattern.compile("^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9]).*\$").toRegex()
        val test1 = pattern.matches(password)
        //val test2 = pattern.matches("1aaaaa")
        //val test3 = pattern.matches("MMaa")
        //val test4 = pattern.matches("111MaPdde")

        //Log.d("Details --",test1.toString()+" "+test2.toString()+" "+test3.toString()+" "+test4.toString()+" "+TextUtils.isEmpty(binding.authPassword.toString()))
        Log.d("Details", test1.toString());
        isValid = test1

        return isValid
    }



    private fun register() {
        var email           = binding.authEmail.text.toString()
        var passwordConfirm = binding.authPasswordConfirm.text.toString()
        var password         = binding.authPassword.text.toString()


        if(password.equals(passwordConfirm)){
        Log.d("DEtails",email+" "+ password +" " +passwordConfirm)
        if (checkConformityFields(binding.authPassword.text.toString())) {
              mViewModel.registerNewUser(email, password)

            //val userInfo = mViewModel.getUserInfo()
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
    }
    }
}