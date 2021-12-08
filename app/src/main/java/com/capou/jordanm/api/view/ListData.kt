package com.capou.jordanm.api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.capou.jordanm.api.viewModel.RestaurantViewModel
import com.capou.jordanm.databinding.ActivityListDataBinding

class ListData : AppCompatActivity() {

    private lateinit var binding :ActivityListDataBinding
    private lateinit var viewModel : RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]
    }
}