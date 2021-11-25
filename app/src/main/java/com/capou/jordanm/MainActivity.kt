package com.capou.jordanm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.capou.jordanm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private var TAG = MainActivity::getLocalClassName.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        // Launch Activity Firebase
        binding.homePageFirebase.setOnClickListener {
          //  val intent = Intent(applicationContext,AuthentificationFirebase::class.java)
          //  startActivity(intent)
            Log.d(TAG,"start firebase")
            Toast.makeText(applicationContext,"start Firebase",Toast.LENGTH_LONG).show()
        }

        //Launch Activity List
        binding.homePageList.setOnClickListener {
          //  val intent = Intent(applicationContext,ListApi::class.java)
          //  startActivity(intent)
            Log.d(TAG,"start List")
            Toast.makeText(applicationContext,"start List",Toast.LENGTH_LONG).show()
        }


        binding.homePageVersion.text = "Numéro de version: "+BuildConfig.VERSION_CODE.toString()


    }
}