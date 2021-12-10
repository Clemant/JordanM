package com.capou.jordanm.api.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.jordanm.api.model.ChuckNorrisUi
import com.capou.jordanm.api.viewModel.ChuckNorrisViewModel
import com.capou.jordanm.databinding.ActivityListDataBinding

class ListData : AppCompatActivity() {


    private lateinit var viewModel: ChuckNorrisViewModel
    private lateinit var binding : ActivityListDataBinding
    private lateinit var adapter : ChuckNorrisAdapter
    private val observer = Observer<List<ChuckNorrisUi>> {
        adapter.submitList(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(ChuckNorrisViewModel::class.java)
        adapter = ChuckNorrisAdapter{
            item,view -> onItemClick(item, view)
        }

        binding.chuckNorrisActivityRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.chuckNorrisActivityRv.adapter = adapter


        binding.chuckNorrisActivityAdd.setOnClickListener {
            viewModel.fetchNewQuote()
        }


        binding.chuckNorrisActivityDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }
    override fun onStart() {
        super.onStart()
        viewModel.mChuckNorrisQuoteLiveData.observe(this, observer)
    }


    override fun onStop() {
        super.onStop()
       viewModel.mChuckNorrisQuoteLiveData.removeObserver(observer)

    }

    private fun onItemClick(objectDataSample: ChuckNorrisUi, view : View) {
        // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, objectDataSample.description, Toast.LENGTH_LONG).show()
    }
}

