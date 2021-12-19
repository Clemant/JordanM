package com.capou.jordanm.api.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.jordanm.MainActivity
import com.capou.jordanm.api.model.ChuckNorrisUi
import com.capou.jordanm.api.model.MyObjectForRecyclerView
import com.capou.jordanm.api.viewModel.ChuckNorrisViewModel
import com.capou.jordanm.architecture.service.MyCloudMessaging
import com.capou.jordanm.databinding.ActivityListDataBinding

class ListData : AppCompatActivity() {


    private lateinit var viewModel: ChuckNorrisViewModel
    private lateinit var binding : ActivityListDataBinding
    private lateinit var adapter : ChuckNorrisAdapter
    private lateinit var sendMessage : MyCloudMessaging
    private val observer = Observer<List<MyObjectForRecyclerView>> {
        adapter.submitList(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(ChuckNorrisViewModel::class.java)
        sendMessage = MyCloudMessaging()
        adapter = ChuckNorrisAdapter{
            item,view, type -> onItemClick(item, view,type)
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

    private fun onItemClick(restaurant: ChuckNorrisUi, view : View, type: String) {
        // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Log.d("Details",type)
        when(type){
            "delete" -> viewModel.deleteById(restaurant.phone_number)
            else -> sendMyNotif(restaurant,view)
        }
    }

    private fun sendMyNotif(restaurant:ChuckNorrisUi, view: View){
        Toast.makeText(this, restaurant.name, Toast.LENGTH_LONG).show()
        var message = restaurant.name+"\n"+restaurant.address
        val intent = Intent(this, MainActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val channelId = "myNotificationChannel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_menu_gallery)
            .setContentTitle("API Name")
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE))
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(0, notificationBuilder.build())
        }



    }
}

