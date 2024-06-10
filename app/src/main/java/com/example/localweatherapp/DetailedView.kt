package com.example.localweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.StringBuilder
import kotlin.system.exitProcess

class DetailedView : AppCompatActivity() {
    private lateinit var detailedView: TextView
    private lateinit var averageView: TextView
    private lateinit var maxView: TextView
    private lateinit var minView: TextView

    private val weatherData = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        detailedView = findViewById(R.id.detailedView)
        averageView = findViewById(R.id.averageView)
        maxView = findViewById(R.id.maxView)
        minView = findViewById(R.id.minView)
        val mainBtn = findViewById<Button>(R.id.mainBtn)
        val exitBtn = findViewById<Button>(R.id.exitDetailedViewBtn)

        intent.getIntegerArrayListExtra("weatherData")?.let { weatherData.addAll(it) }

        displayDetailedView()
        calculateAverage()
        findMax()
        findMin()

        mainBtn.setOnClickListener {
            finish()
        }

        exitBtn.setOnClickListener {
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }
    }

    private fun displayDetailedView(){
        val detailedViewText = StringBuilder()
        for ((index, weatherTemp) in weatherData.withIndex()){
            detailedViewText.append("Day ${index + 1}: $weatherTemp Degrees Celcius\n")
        }
        detailedView.text = detailedViewText.toString()
    }

    private fun calculateAverage(){
        val totalWeatherTemp = weatherData.sum()
        val averageWeatherTemp = if (weatherData.isNotEmpty()){
            totalWeatherTemp / weatherData.size
        } else{
            0
        }
        averageView.text = "Average weather temperature: $averageWeatherTemp Degrees Celcius"
    }

    private fun findMax(){
        val maxWeatherTemp = if (weatherData.isNotEmpty()){
            weatherData.maxOf { weatherData -> weatherData }
        } else{
            0
        }
        maxView.text = "Max weather temperature: $maxWeatherTemp Degrees Celcius"
    }

    private fun findMin(){
        val minWeatherTemp = if (weatherData.isNotEmpty()){
            weatherData.minOf { weatherData -> weatherData }
        } else{
            0
        }
        minView.text = "Min weather temperature: $minWeatherTemp Degrees Celcius"
    }
}