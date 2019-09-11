package com.example.sensorapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val resultView = findViewById<TextView>(R.id.resultsTextView)

        stopButton.isVisible = false
        resultView.text = "Offline"

        startButton.setOnClickListener {
            startButton.isVisible = false
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
            stopButton.isVisible = true

        }

        stopButton.setOnClickListener {
            stopButton.isVisible = false
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER))
            resultView.text = "Offline"
            startButton.isVisible = true

        }

    }

    override fun onSensorChanged(event: SensorEvent) {
        resultsTextView.text = "X : \t${event.values[0]} \n\n" +
                "Y : \t${event.values[1]} \n\n" +
                "Z : \t${event.values[2]} \n\n"
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        
    }
}
