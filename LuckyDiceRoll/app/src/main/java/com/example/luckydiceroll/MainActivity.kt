package com.example.luckydiceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { pickNumber() }
    }

    private fun pickNumber() {
        val myFirstRoll = Roll(10)
        val rollResult = myFirstRoll.roll()
        val luckyNumber = 5
        val resultTextView: TextView = findViewById(R.id.textView)
        when (rollResult) {
            luckyNumber -> println("You won!")
            1 -> println("So sorry! You rolled a ${rollResult}. Try again!")
            2 -> println("Sadly, you rolled a ${rollResult}. Try again!")
            3 -> println("Unfortunately, you rolled a ${rollResult}. Try again!")
            5 -> println("Don't cry! You rolled a ${rollResult}. Try again!")
            6 -> println("Apologies! you rolled a ${rollResult}. Try again!")
            else ->{
                println("OOF! $rollResult is too high. Try again!")
            }
        }
    }

    class Roll(private val numSides: Int) {

        fun roll(): Int {
            return (1..numSides).random()
        }
    }
}