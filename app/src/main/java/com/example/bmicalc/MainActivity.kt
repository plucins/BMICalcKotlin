package com.example.bmicalc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.homeButton)
        button?.setOnClickListener(View.OnClickListener { openCalcActivity() })
    }

    fun openCalcActivity() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
    }
}