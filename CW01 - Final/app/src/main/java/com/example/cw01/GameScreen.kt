package com.example.cw01

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class GameScreen : AppCompatActivity() {

    //initializing the global variables.
    var correctNum = 0
    var questionNum = 0
    var fiveCorrectCount = 0
    var countDown: CountDownTimer? = null
    var leftTime :Long = 0

    var obj = Calculation()
    var obj2 = Calculation()
    var leftExpressionValue = obj.finalOperatorValue()
    var leftExpression = obj.stringExpression()
    var rightExpressionValue = obj2.finalOperatorValue()
    var rightExpression = obj2.stringExpression()
    var answer = ""

    //initializing the TextViews and the Buttons as global variables.
    lateinit var txtLeftExpression: TextView
    lateinit var txtRightExpression: TextView
    lateinit var btnGreater: Button
    lateinit var btnEqual: Button
    lateinit var btnLess: Button
    lateinit var txtAnswer: TextView
    lateinit var timerCountdown: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        //assigning the TextFields and Buttons for the variables that initialized in the globe.
        txtLeftExpression = findViewById(R.id.txtLeftExpression)
        txtRightExpression = findViewById(R.id.txtRightExpression)
        btnGreater = findViewById(R.id.btnGreater)
        btnEqual = findViewById(R.id.btnEqual)
        btnLess = findViewById(R.id.btnLess)
        txtAnswer = findViewById(R.id.txtAnswer)
        timerCountdown = findViewById(R.id.txtTimer)

        //checking whether a system configuration is done or not. and if not starting the countdown timer and displaying the expressions in the TextViews.
        if (savedInstanceState == null) {

            startCountdownTimer(50000)
            txtLeftExpression.text = leftExpression
            txtRightExpression.text = rightExpression
        }

        //creating what to do when the btnGreater is clicked.
        btnGreater.setOnClickListener {
            if (leftExpressionValue > rightExpressionValue) {
                answer = "Correct"
                txtAnswer.text = answer
                txtAnswer.setTextColor(Color.parseColor("#00ff00"))
                correctNum += 1
                fiveCorrectCount += 1
                questionNum += 1

                //after 5 correct answers giving the player additional 10s by calling the timerUpdate().
                if (fiveCorrectCount == 5) {
                    timerUpdate()
                    fiveCorrectCount = 0
                }
            }
            else {
                answer = "Wrong"
                txtAnswer.text = answer
                txtAnswer.setTextColor(Color.parseColor("#ff0000"))
                questionNum += 1
            }
            obj = Calculation()
            obj2 = Calculation()
            leftExpressionValue = obj.finalOperatorValue()
            leftExpression = obj.stringExpression()
            rightExpressionValue = obj2.finalOperatorValue()
            rightExpression = obj2.stringExpression()

            txtLeftExpression.text = leftExpression
            txtRightExpression.text = rightExpression

        }

        //creating what to do when the btnEqual is clicked.
        btnEqual.setOnClickListener {
            if (leftExpressionValue == rightExpressionValue) {
                answer = "Correct"
                txtAnswer.text = answer
                txtAnswer.setTextColor(Color.parseColor("#00ff00"))
                correctNum += 1
                fiveCorrectCount += 1
                questionNum += 1

                //after 5 correct answers giving the player additional 10s by calling the timerUpdate().
                if (fiveCorrectCount == 5) {
                    timerUpdate()
                    fiveCorrectCount = 0
                }
            }
            else {
                answer = "Wrong"
                txtAnswer.text = answer
                txtAnswer.setTextColor(Color.parseColor("#ff0000"))
                questionNum += 1
            }
            obj = Calculation()
            obj2 = Calculation()
            leftExpressionValue = obj.finalOperatorValue()
            leftExpression = obj.stringExpression()
            rightExpressionValue = obj2.finalOperatorValue()
            rightExpression = obj2.stringExpression()

            txtLeftExpression.text = leftExpression
            txtRightExpression.text = rightExpression

        }

        //creating what to do when the btnLess is clicked.
        btnLess.setOnClickListener {
            if (leftExpressionValue < rightExpressionValue) {
                answer = "Correct"
                txtAnswer.text = answer
                txtAnswer.setTextColor(Color.parseColor("#00ff00"))
                correctNum += 1
                fiveCorrectCount += 1
                questionNum += 1

                //after 5 correct answers giving the player additional 10s by calling the timerUpdate().
                if (fiveCorrectCount == 5) {
                    timerUpdate()
                    fiveCorrectCount = 0
                }
            }
            else {
                answer = "Wrong"
                txtAnswer.text = answer
                txtAnswer.setTextColor(Color.parseColor("#ff0000"))
                questionNum += 1
            }
            obj = Calculation()
            obj2 = Calculation()
            leftExpressionValue = obj.finalOperatorValue()
            leftExpression = obj.stringExpression()
            rightExpressionValue = obj2.finalOperatorValue()
            rightExpression = obj2.stringExpression()

            txtLeftExpression.text = leftExpression
            txtRightExpression.text = rightExpression

        }
    }

    //creating the countdown method.
    private fun startCountdownTimer (futureMillis : Long) {

        countDown = object : CountDownTimer(futureMillis,1000) {

            override fun onTick(leftMillis: Long) {
                leftTime = leftMillis
                timerCountdown.text = "${leftMillis/1000}"
            }

            override fun onFinish() {
                val intent5 = Intent(this@GameScreen, GameOver::class.java)
                intent5.putExtra("numberOfCorrectAnswers",correctNum)
                intent5.putExtra("numberOfQuestions",questionNum)
                startActivity(intent5)
            }
        }.start()
    }

    //the method to add extra 10seconds for each and every 5 correct answers.
    private fun timerUpdate () {
        countDown?.cancel()
        leftTime += 10000
        startCountdownTimer(leftTime)
    }

    //directing the back button press to the main page.
    override fun onBackPressed() {
        val intent3 = Intent(this,MainActivity::class.java)
        startActivity(intent3)
    }

    //saving the data of variables and TextViews just a moment before a system configuration happens.
    override fun onSaveInstanceState(outState: Bundle) {

        outState.putInt("correctNum",correctNum)
        outState.putInt("questionsNum", questionNum)
        outState.putInt("fiveCorrectCount",fiveCorrectCount)
        outState.putLong("leftTime",leftTime)
        countDown?.cancel()

        outState.putInt("leftExpressionValue",leftExpressionValue)
        outState.putString("leftExpression", txtLeftExpression.text.toString())
        outState.putInt("rightExpressionValue",rightExpressionValue)
        outState.putString("rightExpression", txtRightExpression.text.toString())

        outState.putString("answer",answer)
        super.onSaveInstanceState(outState)
    }

    //getting the saved data back to the programme after the system configuration.
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        //getting back the correct number of answers and the total questions answered.
        val correctNum = savedInstanceState.getInt("correctNum")
        this.correctNum = correctNum
        val questionNum = savedInstanceState.getInt("questionsNum")
        this.questionNum = questionNum

        //getting back the left expression value and setting the left expression to a new variable.
        val leftExpressionValue = savedInstanceState.getInt("leftExpressionValue")
        this.leftExpressionValue = leftExpressionValue
        val leftExpression = savedInstanceState.getString("leftExpression")
        if (leftExpression != null) {
            this.leftExpression = leftExpression
        }

        //getting back the right expression value and setting the right expression to a new variable.
        val rightExpressionValue = savedInstanceState.getInt("rightExpressionValue")
        this.rightExpressionValue = rightExpressionValue
        val rightExpression = savedInstanceState.getString("rightExpression")
        if (rightExpression != null) {
            this.rightExpression = rightExpression
        }

        //setting the Correct/Wrong TextView to a new variable.
        val answer = savedInstanceState.getString("answer")
        if (answer != null) {
            this.answer = answer
        }

        //restarting the countdown from the stopped time before the system configuration.
        val leftTime = savedInstanceState.getLong("leftTime")
        this.leftTime = leftTime
        startCountdownTimer(this.leftTime)
        fiveCorrectCount = savedInstanceState.getInt("fiveCorrectCount")

        //displaying the math expressions and the answer in TextView.
        txtLeftExpression.text = leftExpression
        txtRightExpression.text = rightExpression
        txtAnswer.text = answer
        if (answer == "Correct") {
            txtAnswer.setTextColor(Color.parseColor("#00ff00"))
        }
        else {
            txtAnswer.setTextColor(Color.parseColor("#ff0000"))
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

}
