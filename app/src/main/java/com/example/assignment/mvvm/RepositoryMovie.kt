package com.example.assignment.mvvm


import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.assignment.Util.Utilities
import com.example.assignment.api.ApiInterface
import com.example.assignment.database.MovieDatabase
import com.example.assignment.model.MovieData

class RepositoryMovie(private val apiInterface: ApiInterface, private val movieDatabase: MovieDatabase, private val context: Context) {

    private val mutableMovieList = MutableLiveData<MovieData?>()

    val movieLiveData : MutableLiveData<MovieData?>
    get() = mutableMovieList

    suspend fun getAllMovieData(){

        if (Utilities.isNetworkConnected(context)){
            val result = apiInterface.getMovieList().body()
            if (result != null){
                // for adding in databases
               //movieDatabase.getRoomDao().insertMovieList(result.Movie_List)

                mutableMovieList.postValue(result)
            }
        }else{
            /*val movie = movieDatabase.getRoomDao().getDatabaseMovie()
            val movieList = MovieData(listOf(movie))
            mutableMovieList.postValue(movieList)*/
        }
    }

    // for rest of movie

    private val mutableLiveDataForRest = MutableLiveData<MovieData?>()

    val movieLiveRestData : MutableLiveData<MovieData?>
    get() = mutableLiveDataForRest

    suspend fun getRestMovieData(){
        val resultRest = apiInterface.getRestMovieList().body()
        if (resultRest != null){
            mutableLiveDataForRest.postValue(resultRest)
        }
    }

}