package com.github.jairrab.moshiexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.squareup.moshi.Moshi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cards = listOf(
            Card('4', Suit.CLUBS),
            Card('A', Suit.HEARTS)
        )

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)

        button1.setOnClickListener {
            val cardsJson = objToJson(cards)
            Toast.makeText(this, cardsJson, Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener {
            val cardsJson = objToJson(cards)
            val cardsObj = jsonToList<Card>(cardsJson!!)
            Toast.makeText(this, cardsObj.toString(), Toast.LENGTH_LONG).show()
        }
    }
}