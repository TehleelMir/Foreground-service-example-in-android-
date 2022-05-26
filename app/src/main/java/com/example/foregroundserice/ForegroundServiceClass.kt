package com.example.foregroundserice

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.foregroundserice.MyApplication.Companion.CHANNEL_ID

class ForegroundServiceClass: Service() {

    /*
        this method will be called when the first time we will create our service
     */
    override fun onCreate() {
        super.onCreate()
    }

    /*
        this method will be called everytime we call start service
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val str = intent?.extras?.getString("key") ?: ""
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivities(this, 0, arrayOf(notificationIntent), 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Example Service")
            .setContentText(str)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        // do some heavy work here on a new background thread, because by default onStartCommand runs on UI thread i.e. main thread.
        // then call stopSelf() otherwise the service will keep running....

        return START_STICKY // mean that our service will start again if it got canceled but the intent will be null
        //return START_NOT_STICKY // mean our service will just start and won't start again
        //return START_REDELIVER_INTENT // mean that our service will start again and also the same intent will be send which we sent it first.
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /*
        this method is Mandatory to override but we use this method only for
        bound services, bound service are those services where other components can communicated back and forth by binding to it.
     */
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}