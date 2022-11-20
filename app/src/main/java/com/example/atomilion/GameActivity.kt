package com.example.atomilion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.opencsv.CSVReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import com.example.atomilion.databinding.ActivityGameBinding

public class GameActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityGameBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 5

    private val quizData = mutableListOf(
        // pytanie, odp1, odp2, odp3, odp4
        mutableListOf("Antracyt jest alotropową odmianą...","Węgla","Kadmu","Złota","Glinu"),
        mutableListOf("Radioaktywny jest...","Uran","Hel","Azot","Argon"),
        mutableListOf("Metalem ciężkim jest...","Rtęć","Żelazo","Srebro","Magnez"),
        mutableListOf("Skóra się robi niebieska od nadmiaru...","Srebra","Krzemu","Złota","Niklu"),
        mutableListOf("Czy Pan Wojciech jest super?","Oczywiście, że tak!","Nie","Pierwsze słyszę","Pogadajmy o czymś innym"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        quizData.shuffle()

        showNextQuiz()
    }

    fun showNextQuiz() {
        // Odświeżanie countLabel
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        val quiz = quizData[0]

        // ustawienie pytania i poprawnej odpowiedzi

        binding.questionLabel.text = quiz[0]
        rightAnswer = quiz[1]

        //Wyrzucenie wyrazu "pytanie" z quizu
        quiz.removeAt(0)

        // Losowanie odpowiedzi i wyborów.
        quiz.shuffle()


        // Ustawienie wyborów.
        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]

        // Usunięcie quizu z quizData

        quizData.removeAt(0)

    }

    fun checkAnswer(view: View) {
        // Wciskanie przycisku
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
                if (btnText == rightAnswer) {
                    // Correct
                    alertTitle = "Dobra odpowiedź!"
                    rightAnswerCount++
                } else {
                    // Wrong
                    alertTitle = "Niestety..."
                }

        // Komunikat
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Prawidłowa odpowiedź: $rightAnswer")
            .setPositiveButton("OK") {dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    fun checkQuizCount() {
        if (quizCount == QUIZ_COUNT) {
            // Show Result
            val intent = Intent(this@GameActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
            startActivity(intent)

        } else {
            quizCount++
            showNextQuiz()
        }
    }
}