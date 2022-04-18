package com.g4.lohotron

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chronometer: Chronometer = findViewById(R.id.chronometer)
        var onWork : Boolean = false
        val buttonPlay: ImageButton = findViewById(R.id.buttonPlay)
        val buttonPause : ImageButton = findViewById(R.id.buttonPause)
        val buttonStop : ImageButton = findViewById(R.id.buttonStop)

        buttonPlay.setOnClickListener{

            chronometer.start()
            onWork = true
            buttonPause.isVisible = true
            buttonStop.isVisible = true
            buttonPlay.isVisible = false

        }

        buttonPause.setOnClickListener{

            chronometer.stop()
            onWork = false
            buttonPause.isVisible = false
            buttonPlay.isVisible = true

        }

        buttonStop.setOnClickListener(){

            chronometer.base = SystemClock.elapsedRealtime()
            buttonPause.isVisible = false
            buttonPlay.isVisible = true
            buttonStop.isVisible = false
            chronometer.stop()
            onWork = false

        }


    }
}