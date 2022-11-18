package com.example.atomilion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.opencsv.CSVReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class GameActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar?.hide()

        val buttonA = findViewById<Button>(R.id.Ans_A)
        val buttonB = findViewById<Button>(R.id.Ans_B)
        val buttonC = findViewById<Button>(R.id.Ans_C)
        val buttonD = findViewById<Button>(R.id.Ans_D)

        buttonA.text="OdpA"

        val streamReader = InputStreamReader(assets.open("questions.csv"))
        val reader = CSVReader(streamReader)

        var line = reader.readNext()
        for (value in line){
            println(value)
        }
    }
}