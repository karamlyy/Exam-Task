package com.example.examtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), MovieAdapter.Functions {

    private val apiKey = "4fae678e0bf0ca0ce26f68efa69e3328"
    private val api: API = API.instance

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchButton: Button
    private lateinit var searchField: EditText
    private lateinit var popularButton: Button
    private lateinit var upcomingButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter(this)
        recyclerView = findViewById(R.id.recyclerView)
        searchButton = findViewById(R.id.searchButton)
        searchField = findViewById(R.id.seachField)
        popularButton = findViewById(R.id.popularMoviesButton)
        upcomingButton = findViewById(R.id.upcomingMoviesButton)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = movieAdapter

        searchButton.setOnClickListener {
            runBlocking { launch { async {
                try {
                    val result = api.search(
                        this@MainActivity.apiKey,
                        this@MainActivity.searchField.text.toString()
                    )
                    runOnUiThread {
                        movieAdapter.set(result.results)
                    }
                } catch (t: Throwable) {
                    print(t)
                }
            } } }
        }

        popularButton.setOnClickListener {
            runBlocking { launch { async {
                try {
                    val result = api.popular(
                        this@MainActivity.apiKey
                    )
                    runOnUiThread {
                        movieAdapter.set(result.results)
                    }
                } catch (t: Throwable) {
                    print(t)
                }
            } } }
        }

        upcomingButton.setOnClickListener {
            runBlocking { launch { async {
                try {
                    val result = api.upcoming(
                        this@MainActivity.apiKey
                    )
                    runOnUiThread {
                        movieAdapter.set(result.results)
                    }
                } catch (t: Throwable) {
                    print(t)
                }
            }
            }
            }
        }
    }
}