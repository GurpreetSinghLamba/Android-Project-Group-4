package com.example.jobxpress

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        val candidateButton:Button=findViewById(R.id.candidate_btn)
        candidateButton.setOnClickListener{
            val intent=Intent(this,candidateActivity::class.java)
            startActivity(intent)
        }
    }
}