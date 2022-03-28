package com.example.cw01

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNewGame = findViewById<Button>(R.id.btnNewGame)
        val btnAbout = findViewById<Button>(R.id.btnAbout)
        val btnGameRules = findViewById<Button>(R.id.btnGameRules)
        val txtCheck = findViewById<TextView>(R.id.check)     //to set the pop up window as a drop down of the text view.

        //setting the new game button on click listener.
        btnNewGame.setOnClickListener {
            val intent = Intent(this,GameScreen::class.java)
            startActivity(intent)
        }

        //creating an additional button for display the rules of the Game.
        btnGameRules.setOnClickListener {
            val intentRule = Intent(this,StartGame::class.java)
            startActivity(intentRule)
        }

        //creating a pop up window to the about button.
        btnAbout.setOnClickListener {
            val popWindow = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.pop_up_about,null)
            popWindow.contentView = view

            //making the pop up window disappear by clicking the About image.
            val imgAbout = view.findViewById<ImageView>(R.id.imgAbout)
            imgAbout.setOnClickListener{
                popWindow.dismiss()
            }
            popWindow.showAsDropDown(txtCheck)
        }
    }
}
