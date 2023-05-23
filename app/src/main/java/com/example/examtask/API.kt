package com.example.examtask

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



fun buildAPI(): API {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)
}

interface API {
    companion object {
        val instance: API = buildAPI()
    }

    @GET("/3/search/movie")
    suspend fun search(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): ResultJSON<List<Movie>>

    @GET("/3/movie/upcoming")
    suspend fun upcoming(
        @Query("api_key") apiKey: String
    ): ResultJSON<List<Movie>>

    @GET("/3/movie/popular")
    suspend fun popular(
        @Query("api_key") apiKey: String
    ): ResultJSON<List<Movie>>

    @GET("/3/genre/movie/list")
    suspend fun genre(
        @Query("api_key") apiKey: String
    ): GenreJSON
}



