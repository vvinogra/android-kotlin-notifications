package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "MyFirebaseMsgService"

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage?.from}")

        // Check if message contains a data payload.
        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.body!!)
        }

    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String?) {
        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    private fun sendNotification(description: String) {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java)
                    as NotificationManager

        notificationManager.sendNotification(description, applicationContext)
    }

    private fun sendRegistrationToServer(token: String?) {}
}