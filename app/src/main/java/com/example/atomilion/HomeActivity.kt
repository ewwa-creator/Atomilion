package com.example.atomilion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener() {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}