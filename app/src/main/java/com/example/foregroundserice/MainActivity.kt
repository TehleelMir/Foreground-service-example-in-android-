package com.example.foregroundserice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val button   = findViewById<Button>(R.id.start)
        val button2  = findViewById<Button>(R.id.stop)
        val editText = findViewById<EditText>(R.id.editText)

        button.setOnClickListener {
            val str = editText.text.toString()
            val intentService = Intent(this, ForegroundServiceClass::class.java)
            intentService.putExtra("key", str)

            /*
                Inorder to start a foreground service when the app is running we use
                startService method, but if we want to start a foreground service when the app is
                not running then at that time we have to use
                startForegroundService() and after doing it android gives us 5sec of time window to call startForeground in our service class
                if it took more then that, then the service will be killed and won't start.

                ContextCompat.startForegroundService(this, intent) 
             */
            startService(intentService)
        }

        button2.setOnClickListener {
            val intentService = Intent(this, ForegroundServiceClass::class.java)
            stopService(intentService)
        }
    }
}