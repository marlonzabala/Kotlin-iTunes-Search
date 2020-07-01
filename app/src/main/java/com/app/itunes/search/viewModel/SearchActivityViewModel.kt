package com.app.itunes.search.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.itunes.search.network.model.ItunesItem
import com.app.itunes.search.repository.PreferenceRepository
import com.app.itunes.search.repository.SearchActivityRepository

/**
 * Handles logic of search activity
 */
class SearchActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SearchActivityRepository(application)
    private val preferenceRepository = PreferenceRepository(application)
    val showProgress : LiveData<Boolean>
    val itunesItemList : LiveData<List<ItunesItem>>
    val searchTerm = MutableLiveData<String>()

    init {
        this.showProgress = repository.showProgress
        this.itunesItemList = repository.ItunesItemList
    }

    fun searchMovies(term : String) {
        repository.searchMovies(term)
        preferenceRepository.setLastSearchTerm(term)
    }

    fun setViewingSearch(){
        preferenceRepository.setIsViewingDetails(false)
    }

    fun init() {
        val lastSearchTerm = preferenceRepository.getLastSearchTerm()

        if(lastSearchTerm.isNotEmpty()) {
            searchTerm.value = lastSearchTerm
        } else {
            searchTerm.value = "Star"
        }
    }
}