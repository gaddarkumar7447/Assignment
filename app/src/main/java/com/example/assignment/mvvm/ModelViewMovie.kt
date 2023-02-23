package com.example.assignment.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.model.MovieData
import kotlinx.coroutines.launch

class ModelViewMovie(private val repositoryMovie: RepositoryMovie) : ViewModel() {

    init {
        viewModelScope.launch {
            repositoryMovie.getAllMovieData()
        }

        // for restMovie
        viewModelScope.launch {
            repositoryMovie.getRestMovieData()
        }
    }
    val movieLiveData : MutableLiveData<MovieData?>
    get() = repositoryMovie.movieLiveData


    // getRest movie data
    val movieDataRest : MutableLiveData<MovieData?>
    get() = repositoryMovie.movieLiveRestData
}