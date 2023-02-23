package com.example.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.model.Movie

class AdapterShowMovie(private val context: Context, private val listMovie: List<Movie> ) : RecyclerView.Adapter<AdapterShowMovie.ViewShowMovie>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewShowMovie {
        return ViewShowMovie(LayoutInflater.from(context).inflate(R.layout.card_view, parent,false))
    }

    override fun onBindViewHolder(holder: ViewShowMovie, position: Int) {
        val currentItem = listMovie[position]
        holder.cast.text = currentItem.Cast
        holder.title.text = currentItem.Title
        holder.imdb.text = currentItem.IMDBID
        holder.time.text = currentItem.Runtime
        holder.generic.text = currentItem.Genres

        Glide.with(context).load(currentItem.Movie_Poster).into(holder.image)

    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    class ViewShowMovie(view : View) : RecyclerView.ViewHolder(view) {
        val title : TextView = view.findViewById(R.id.titleMovie)
        val cast : TextView = view.findViewById(R.id.Cast)
        val time : TextView = view.findViewById(R.id.Runtime)
        val generic : TextView = view.findViewById(R.id.Genres)
        val imdb : TextView = view.findViewById(R.id.IMDBID)
        val image : ImageView = view.findViewById(R.id.image)

    }
}