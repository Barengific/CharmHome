package com.barengific.charmhome

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging


import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Undertow
import org.http4k.server.asServer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Firebase.messaging.isAutoInitEnabled = true
        FirebaseMessaging.getInstance()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            val TAG = "aaaaaToken"
            if (!task.isSuccessful) {
                Log.d(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.d(TAG, token)
        })
        val tokenss =  FirebaseMessaging.getInstance().getToken()
        createNotificationChannel()
        sendNoti()



        
    }


    private fun sendNoti(){
        val CHANNEL_ID = "mychannel_1"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("yup")
            .setContentText("ahaha")
            .setSound(defaultSoundUri)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(12345, builder.build())
            Log.d("aaaMainNoti", "notified")
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name ="notiChannelName"
            val descriptionText = "this is basic channel desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val CHANNEL_ID = "mychannel_1"
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }//TODO
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}