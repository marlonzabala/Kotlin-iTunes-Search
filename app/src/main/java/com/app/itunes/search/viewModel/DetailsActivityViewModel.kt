package com.app.itunes.search.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.app.itunes.search.network.model.ItunesItem
import com.app.itunes.search.repository.DetailsActivityRepository
import com.app.itunes.search.repository.PreferenceRepository

/**
 * Handles logic of details activity
 */
class DetailsActivityViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = DetailsActivityRepository(application)
    private val preferenceRepository = PreferenceRepository(application)
    private val showProgress : LiveData<Boolean>
    val itunesItem : LiveData<ItunesItem>

    init{
        this.showProgress = repository.showProgress
        this.itunesItem = repository.itunesItem
    }

    fun loadMovie() {
        repository.searchMovies(
            preferenceRepository.getLastSearchTerm(),
            preferenceRepository.getLastPosition())

        preferenceRepository.setIsViewingDetails(true)
    }
}