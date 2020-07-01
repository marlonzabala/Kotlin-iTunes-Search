package com.app.itunes.search.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.app.itunes.search.R
import com.app.itunes.search.network.BASE_URL
import com.app.itunes.search.network.ItunesNetwork
import com.app.itunes.search.network.model.ItunesItem
import com.app.itunes.search.network.model.ItunesResult
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Handles movie search values
 */
class SearchActivityRepository(val application: Application){
    val showProgress = MutableLiveData<Boolean>()
    val ItunesItemList = MutableLiveData<List<ItunesItem>>()

    fun changeState () {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun searchMovies(term : String) {
        showProgress.value = true

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ItunesNetwork::class.java)

        service.getMovies(term,"movie","au").enqueue(object : Callback<ItunesResult>{
            override fun onFailure(call: Call<ItunesResult>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, application.getString(R.string.error_result), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ItunesResult>, response: Response<ItunesResult>) {
                ItunesItemList.value = response.body()?.results
                showProgress.value = false
            }
        })
    }
}