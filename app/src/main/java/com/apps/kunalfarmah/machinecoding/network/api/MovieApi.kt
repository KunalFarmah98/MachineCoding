package com.apps.kunalfarmah.machinecoding.network.api

import com.apps.kunalfarmah.machinecoding.Constants
import com.apps.kunalfarmah.machinecoding.network.data.Movie
import com.apps.kunalfarmah.machinecoding.network.data.SearchResponse
import com.apps.kunalfarmah.machinecoding.network.data.SearchedMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    //http://www.omdbapi.com/?apikey={API_KEY}&s={SEARCH_STRING}&page={PAGE_NO}
    // https://www.omdbapi.com/?i=tt3896198&apikey=4c6e57b1
    @GET("?")
    suspend fun searchMovie(@Query("s") query:String, @Query("apiKey") apiKey: String? = Constants.API_KEY,  @Query("page") page: String? = "1"): SearchResponse
    @GET("?")
    suspend fun getMovie(@Query("i") imdbId:String, @Query("apiKey") apiKey: String? = Constants.API_KEY) : Movie

}