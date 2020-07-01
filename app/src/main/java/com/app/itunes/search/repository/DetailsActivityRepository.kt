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
 * Handling the data of Details Activity
 */
class DetailsActivityRepository(val application: Application) {
    val showProgress = MutableLiveData<Boolean>()
    val itunesItem = MutableLiveData<ItunesItem>()

    fun searchMovies(term : String, position : Int) {
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
                itunesItem.value = response.body()?.results?.get(position)
                showProgress.value = false
            }
        })
    }
}