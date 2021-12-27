package com.capou.jordanm.architecture.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.MutableLiveData
import com.capou.jordanm.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MyCloudMessaging : FirebaseMessagingService() {

    private var default = MutableLiveData<String>()
    companion object {
        const val TAG = "MyFirebaseMessagingSrv"
    }
    init {

    }
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data);

        }


        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body);
            remoteMessage.notification?.let { sendNotification(it) }
           Log.d(TAG, "  "+remoteMessage.data.keys+" "+remoteMessage.data.values)
            var messageKeyValue =""
           for(resul in remoteMessage.data){
               messageKeyValue = messageKeyValue+" \n"+resul.key+" -->"+ resul.value
           }
            //Toast.makeText(baseContext,messageKeyValue,Toast.LENGTH_LONG).show()
          // default.postValue(messageKeyValue)
        }
    }

    private fun sendNotification(remoteMessageNotification: RemoteMessage.Notification) {
        val intent = Intent(this, MainActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val channelId = "myNotificationChannel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_menu_gallery)
            .setContentTitle(remoteMessageNotification.title)
            .setContentText(remoteMessageNotification.body)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


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