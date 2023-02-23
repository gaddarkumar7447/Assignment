package com.example.assignment.model

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName ("Movie List") val Movie_List: List<Movie>
)
