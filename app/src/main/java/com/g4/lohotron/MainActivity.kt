package com.g4.lohotron

import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val delays = arrayOf(30, 5, 10, 15, 20, 25, 60, 90, 120, 180)
        var delay : Long = 30
        var timeWhenStop : Long = 0
        var isWorking = false
        val chronometer: Chronometer = findViewById(R.id.chronometer)
        val buttonPlayPause: ExtendedFloatingActionButton = findViewById(R.id.eFAb_play_pause)
        val buttonStop : ExtendedFloatingActionButton = findViewById(R.id.eFab_stop)
        val timer = Timer(true)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_dropdown_item, delays)
        spinner.adapter =arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                delay = delays[position].toLong()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        buttonPlayPause.setOnClickListener{
            if(!isWorking){
                chronometer.base = SystemClock.elapsedRealtime() + timeWhenStop
                chronometer.start()
                buttonStop.isVisible = true
                isWorking = true
                buttonPlayPause.setIconResource(R.drawable.play)

                val mediaPlayer = MediaPlayer.create(this, R.raw.beep)
                Toast.makeText(applicationContext, delay.toString(), Toast.LENGTH_SHORT).show()
                timer.schedule( timerTask { mediaPlayer.start() }, TimeUnit.SECONDS.toMillis(delay), TimeUnit.SECONDS.toMillis(delay))

            } else{
                chronometer.stop()
                timeWhenStop = chronometer.base - SystemClock.elapsedRealtime()
                buttonPlayPause.setIconResource(R.drawable.pause)
                isWorking = false
                timer.cancel()
                timer.purge()
            }
        }
        buttonStop.setOnClickListener{
            chronometer.stop()
            timeWhenStop = 0
            buttonStop.isVisible = false
            buttonPlayPause.setIconResource(R.drawable.pause)
            isWorking = false
            timer.cancel()
            timer.purge()
        }

    }
}