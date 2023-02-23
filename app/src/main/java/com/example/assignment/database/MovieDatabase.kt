package com.example.assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun getRoomDao() : MovieDao

    companion object{
        private var INSTANCE : MovieDatabase ?= null

        fun getInstanceOfDataBase(context: Context) : MovieDatabase{
            synchronized(this){
                if (INSTANCE == null){
                    val instance = Room.databaseBuilder(context, MovieDatabase::class.java, "movieDB").build()
                    INSTANCE = instance
                }
            }
            return INSTANCE!!
        }
    }

}