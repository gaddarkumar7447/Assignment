package com.example.assignment.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movieDB")
data class Movie(
    @SerializedName("Cast") val Cast: String,
    @SerializedName("Genres") val Genres: String,

    @PrimaryKey val IMDBID: String,

    @SerializedName("Movie Poster") val Movie_Poster: String,
    @SerializedName("Runtime") val Runtime: String,
     val Title: String,
)
