package com.example.firebasepushnotification

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TOKENID", "token")
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d("PAYLOADS", "${message.data}")

        getFirebaseMessage(message.notification?.title!!, message.notification?.body!!)
    }

    private fun getFirebaseMessage(title: String, msg: String) {
        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, "myFirebaseChannel")
                .setSmallIcon(R.drawable.ic_notifications_active)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

        var manager: NotificationManagerCompat = NotificationManagerCompat.from(this)
        manager.notify(101, builder.build())
    }
}