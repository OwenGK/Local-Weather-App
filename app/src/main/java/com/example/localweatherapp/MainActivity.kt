package com.example.localweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var userInput: TextView
    private lateinit var addBtn: Button
    private lateinit var clearBtn: Button
    private lateinit var nextBtn: Button

    private val weatherData = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userInput = findViewById(R.id.userInput)
        addBtn = findViewById(R.id.addBtn)
        clearBtn = findViewById(R.id.clearBtn)
        nextBtn = findViewById(R.id.detailedViewBtn)

        addBtn.setOnClickListener {
            add()
        }

        clearBtn.setOnClickListener {
            clearData()
        }

        nextBtn.setOnClickListener {
            if (weatherData.isEmpty()){
                Toast.makeText(this, "Please input weather temperatures", Toast.LENGTH_LONG).show()
            } else{
                val intent = Intent(this, DetailedView::class.java).apply {
                    putIntegerArrayListExtra("weatherData", ArrayList(weatherData))
                }
                startActivity(intent)
            }
        }

    }

    private fun add(){
        val weatherTempString = userInput.text.toString()
        if (weatherTempString.isNotEmpty()){
            try {
                val weatherTemp = weatherTempString.toInt()
                if (weatherTemp >= -20){
                    weatherData.add(weatherTemp)
                    userInput.text = ""
                    Toast.makeText(this, "Weather update added successfully", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "Invalid temperature input!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: NumberFormatException){
                Toast.makeText(this, "Invalid temperature input!", Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(this, "Please enter weather temperatures", Toast.LENGTH_SHORT).show()
        }

    }

    private fun clearData(){
        weatherData.clear()
        Toast.makeText(this, "Data cleared", Toast.LENGTH_LONG).show()

    }
}


