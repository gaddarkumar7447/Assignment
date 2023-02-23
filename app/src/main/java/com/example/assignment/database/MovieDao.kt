package com.example.assignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment.model.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovieList(movie: List<Movie>)

    @Query("SELECT * FROM movieDB")
    suspend fun getDatabaseMovie() : Movie

}