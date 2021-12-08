package com.capou.jordanm.api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capou.jordanm.databinding.ActivityListDataBinding

class ListData : AppCompatActivity() {

    private lateinit var binding :ActivityListDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}