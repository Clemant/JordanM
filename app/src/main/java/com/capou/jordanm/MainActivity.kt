package com.capou.jordanm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.capou.jordanm.api.view.ListData
import com.capou.jordanm.databinding.ActivityMainBinding
import com.capou.jordanm.firebase_all.view.AuthentificationFirebase

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private var TAG = MainActivity::getLocalClassName.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  Glide.with(applicationContext).load("https://picsum.photos/200/300?random=1").into(binding.avatar)
        //Glide.with(applicationContext).load("https://cdn.icon-icons.com/icons2/2630/PNG/512/avatar_man_boy_people_black_race_african_icon_159091.png").into(binding.avatar)
        Glide.with(applicationContext).load("https://cdn.icon-icons.com/icons2/2643/PNG/512/man_boy_people_avatar_user_person_black_skin_tone_icon_159355.png").into(binding.avatar)

    }

    override fun onStart() {
        super.onStart()

        // Launch Activity Firebase
        binding.homePageFirebase.setOnClickListener {
            val intent = Intent(applicationContext,AuthentificationFirebase::class.java)
            startActivity(intent)
            Log.d(TAG,"start firebase")
            Toast.makeText(applicationContext,"start Firebase",Toast.LENGTH_LONG).show()
        }

        //Launch Activity List
        binding.homePageList.setOnClickListener {
            val intent = Intent(applicationContext,ListData::class.java)
           startActivity(intent)
            Log.d(TAG,"start List")
            Toast.makeText(applicationContext,"start List",Toast.LENGTH_LONG).show()
        }


        binding.homePageVersion.text = "Numéro de version: "+BuildConfig.VERSION_CODE.toString()


    }
}