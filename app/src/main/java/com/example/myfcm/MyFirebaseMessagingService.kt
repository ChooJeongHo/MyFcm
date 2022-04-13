package com.example.myfcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        var CHANNEL_ID = "id"
        var CHANNEL_NAME = "name"

        val notificationManager = NotificationManagerCompat.from(
            applicationContext
        )

        var builder: NotificationCompat.Builder? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            builder = NotificationCompat.Builder(applicationContext)
        }

        val title: String = p0.getNotification()!!.getTitle()!!
        val body: String = p0.getNotification()!!.getBody()!!

        builder!!.setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)

        val notification: Notification = builder!!.build()
        notificationManager.notify(1000, notification)

        Log.d("aabb", "come!")
    }
}