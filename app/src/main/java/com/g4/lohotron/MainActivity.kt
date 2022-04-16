package com.g4.lohotron

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chronometer: Chronometer = findViewById(R.id.chronometer)
        var onWork : Boolean = false
        val button: ImageButton = findViewById(R.id.button)
        button.setOnClickListener {
            if(!onWork){
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
                onWork = true
            }
            else {
                chronometer.stop()
                onWork = false

            }
        }
    }
}