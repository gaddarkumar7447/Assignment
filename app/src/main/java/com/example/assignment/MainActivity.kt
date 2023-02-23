package com.example.assignment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment.adapter.AdapterShowMovie
import com.example.assignment.api.ApiInterface
import com.example.assignment.api.ApiUtilities
import com.example.assignment.database.MovieDatabase
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.Movie
import com.example.assignment.model.MovieData
import com.example.assignment.mvvm.ModelViewMovie
import com.example.assignment.mvvm.RepositoryMovie
import com.example.assignment.mvvm.ViewModelFactoryMovie
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var listMovie : MutableList<Movie>
    private lateinit var adapterShowMovie: AdapterShowMovie
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        // Call via mvvm
        listMovie = mutableListOf()
        adapterShowMovie = AdapterShowMovie(this, listMovie)

        val apiInterface = ApiUtilities.getIntence().create(ApiInterface::class.java)
        //creating database instance

        val database = MovieDatabase.getInstanceOfDataBase(this)

        val respository = RepositoryMovie(apiInterface,database, this)
        // call via viewModel factory
        /*val viewModelFactoryMovie = ViewModelFactoryMovie(respository)
        viewModelFactoryMovie.create(ModelViewMovie::class.java).movieLiveData.observe(this, Observer {
            it?.Movie_List
        })*/
        val modelViewMovie = ModelViewMovie(respository)

        modelViewMovie.movieLiveData.observe(this, Observer { it ->
            it?.Movie_List?.listIterator()?.forEach { movie ->
                val title = movie.Title
                val cast = movie.Cast
                val image = movie.Movie_Poster
                val runtime = movie.Runtime
                val generic = movie.Genres
                val imdb = movie.IMDBID
                Log.d("url", "url : $image")

                val list = Movie(cast, generic,imdb,image,runtime,title)
                listMovie.add(list)
            }

            Log.d("ListOfMovie", "url : $listMovie")
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = adapterShowMovie
            adapterShowMovie.notifyDataSetChanged()
        })

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(dy)) {
                    binding.loadMore.visibility = View.VISIBLE
                    binding.loadMore.setOnClickListener {
                        modelViewMovie.movieDataRest.observe(this@MainActivity, Observer {
                            it?.Movie_List?.listIterator()?.forEach { movie ->
                                val title = movie.Title
                                val cast = movie.Cast
                                val image = movie.Movie_Poster
                                val runtime = movie.Runtime
                                val generic = movie.Genres
                                val imdb = movie.IMDBID
                                Log.d("url", "url : $image")

                                val list = Movie(cast, generic,imdb,image,runtime,title)
                                listMovie.add(list)
                            }
                            Log.d("ListOfMovie", "url : $listMovie")
                            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                            binding.recyclerView.setHasFixedSize(true)
                            binding.recyclerView.adapter = adapterShowMovie
                            adapterShowMovie.notifyDataSetChanged()
                        })
                        binding.loadMore.visibility = View.GONE
                    }
                }else{
                    binding.loadMore.visibility = View.GONE
                }
            }
        })








        /*// Call bia Retrofit
        val apiInterface = ApiUtilities.getIntence().create(ApiInterface::class.java)
        listMovie = mutableListOf()
        adapterShowMovie = AdapterShowMovie(this, listMovie)
        MainScope().launch {
            apiInterface.getMovieList().body()?.Movie_List?.iterator()?.forEach {
                val title = it.Title
                val cast = it.Cast
                val image = it.Movie_Poster
                val runtime = it.Runtime
                val generic = it.Genres
                val imdb = it.IMDBID
                Log.d("url", "url : $image")

                val list = Movie(cast, generic,imdb,image,runtime,title)
                listMovie.add(list)

            }
            //Log.d("DataODfList", "List : $listMovie")

            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = adapterShowMovie
        }*/

    }
}