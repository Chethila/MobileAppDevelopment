package com.example.cw01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtInCorrectScore = findViewById<TextView>(R.id.txtInCorrectScore)

        val correct = intent.getIntExtra("numberOfCorrectAnswers",0).toString()
        val questions = intent.getIntExtra("numberOfQuestions",0).toString()

        txtScore.text = "${correct} out of ${questions}"

        val correct2 = intent.getIntExtra("numberOfCorrectAnswers",0)
        val questions2 = intent.getIntExtra("numberOfQuestions",0)

        val incorrect = questions2 - correct2
        txtInCorrectScore.text = "${incorrect} incorrect answers"

    }

    override fun onBackPressed() {
        var intent4 = Intent(this,MainActivity::class.java)
        startActivity(intent4)
    }
}