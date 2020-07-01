package com.app.itunes.search.network

import com.app.itunes.search.network.model.ItunesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Handling api calls using retrofit library
 */
const val BASE_URL = "https://itunes.apple.com/"

interface ItunesNetwork {

    // removed "&all" because default value is all
    @GET("search?")
    fun getMovies(
        @Query("term") term : String,
        @Query("media") media : String,
        @Query("country") country : String) : Call<ItunesResult>

    @GET("lookup?")
    fun lookUp(@Query("id") id : String) : Call<ItunesResult>
}