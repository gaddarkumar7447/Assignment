package com.example.assignment.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactoryMovie(private val repositoryMovie: RepositoryMovie) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ModelViewMovie(repositoryMovie) as T
    }
}