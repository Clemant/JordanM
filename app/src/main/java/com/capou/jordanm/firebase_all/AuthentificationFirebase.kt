package com.capou.jordanm.firebase_all

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capou.jordanm.databinding.ActivityAuthentificationFirebaseBinding

class AuthentificationFirebase : AppCompatActivity() {

    private lateinit var  binding: ActivityAuthentificationFirebaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthentificationFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
    }
}