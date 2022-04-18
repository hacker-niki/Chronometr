package com.g4.lohotron

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var timeWhenStop : Long = 0
        var isWorkNow = false
        val chronometer: Chronometer = findViewById(R.id.chronometer)
        val buttonPlayPause: ExtendedFloatingActionButton = findViewById(R.id.eFAb_play_pause)
        val buttonStop : ExtendedFloatingActionButton = findViewById(R.id.eFab_stop)

        buttonPlayPause.setOnClickListener{
            if(!isWorkNow){
                chronometer.base = SystemClock.elapsedRealtime() + timeWhenStop;
                chronometer.start();
                buttonStop.isVisible = true
                isWorkNow = true
                buttonPlayPause.setIconResource(R.drawable.play)
            } else{

                chronometer.stop()
                timeWhenStop = chronometer.base - SystemClock.elapsedRealtime()
                buttonPlayPause.setIconResource(R.drawable.pause)
                isWorkNow = false
            }
        }

        buttonStop.setOnClickListener{
            chronometer.stop()
            timeWhenStop = 0
            buttonStop.isVisible = false
            buttonPlayPause.setIconResource(R.drawable.pause)
            isWorkNow = false
        }
    }
}