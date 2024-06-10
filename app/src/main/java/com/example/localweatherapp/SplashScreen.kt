package com.example.localweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.system.exitProcess

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen2)

        val startBtn = findViewById<Button>(R.id.startBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)

        //Button to navigate to main screen
        startBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        //Button to exit application
        exitBtn.setOnClickListener {
            exit()
        }
    }

    //function for exit button
    private fun exit(){
        moveTaskToBack(true)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(1)
    }
}