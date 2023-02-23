package com.example.assignment.api

import com.example.assignment.model.Movie
import com.example.assignment.model.MovieData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface{
    @GET("/1.json")
    suspend fun getMovieList() : Response<MovieData>

    @GET("/2.json")
    suspend fun getRestMovieList() : Response<MovieData>

}