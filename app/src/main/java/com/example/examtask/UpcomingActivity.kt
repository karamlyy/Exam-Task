package com.example.examtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UpcomingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)

        val mainMoviesButton = findViewById<Button>(R.id.mainButton)
        val popularMoviesButton = findViewById<Button>(R.id.popularMoviesButton)

        mainMoviesButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        popularMoviesButton.setOnClickListener {
            val intent = Intent(this, PopularActivity::class.java)
            startActivity(intent)

        }
    }
}