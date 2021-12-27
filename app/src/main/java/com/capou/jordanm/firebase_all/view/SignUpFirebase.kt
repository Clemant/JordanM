package com.capou.jordanm.firebase_all.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capou.jordanm.databinding.ActivityAuthentificationFirebaseBinding
import com.capou.jordanm.databinding.ActivitySignUpFirebaseBinding
import com.capou.jordanm.firebase_all.viewModel.AuthFirebaseViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import java.util.regex.Pattern

class SignUpFirebase : AppCompatActivity() {
    private lateinit var mViewModel: AuthFirebaseViewModel
    private lateinit var binding: ActivitySignUpFirebaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_authentification_firebase)
        binding = ActivitySignUpFirebaseBinding.inflate(layoutInflater);
        mViewModel = ViewModelProvider(this).get(AuthFirebaseViewModel::class.java)
        setContentView(binding.root)
        Snackbar.make(binding.root,"by default",Snackbar.LENGTH_LONG).show()
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
        val pattern = Pattern.compile("^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9]).*\$").toRegex()
        var isValid = pattern.matches(password)
        return isValid
    }



    private fun register() {
        var email            = binding.email.text.toString()
        var passwordConfirm  = binding.passwordConfirm.text.toString()
        var password         = binding.password.text.toString()

        if(!TextUtils.isEmpty(email)){
            var isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
            if(!isEmailValid){
                binding.authEmail.error = "Ceci n'est pas une adresse mail."
            }
        }
        else{
            binding.authEmail.error = "Vous devez saisir une adresse mail."
        }
        if(password.equals(passwordConfirm)){
        if (checkConformityFields(binding.password.text.toString())) {
              mViewModel.registerNewUser(email, password)

            //val userInfo = mViewModel.getUserInfo()
            mViewModel.getMessageListener().observe(this,{
                var test: String?
                if(it.containsKey("success_message")){
                    test = it.get("success_message").toString()
                    val intent = Intent(this,AuthentificationFirebase::class.java)
                    startActivity(intent)
                }
                else{
                   test = it.get("errors_message") .toString()
                }
              //  Toast.makeText(applicationContext,test,Toast.LENGTH_LONG).show()
                Snackbar.make(binding.root,"${test}",Snackbar.LENGTH_LONG).show()
            })

        }
        else{
            binding.authPassword.error = "Doit contenir au moins une lettre majuscule, une lettre minuscule et un chiffre."
        }
    }
        else{
            binding.authPasswordConfirm.error = "Mot de passe différent"
        }
    }
}