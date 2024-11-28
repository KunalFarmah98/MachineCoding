package com.apps.kunalfarmah.machinecoding.repository

import android.util.Log
import com.apps.kunalfarmah.machinecoding.network.api.MovieApi
import com.apps.kunalfarmah.machinecoding.network.data.Movie
import com.apps.kunalfarmah.machinecoding.network.data.SearchResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi): IMovieRepository {
    override suspend fun searchMovie(query: String): SearchResponse {
        return try{
            movieApi.searchMovie(query)
        }
        catch (e: Exception){
            Log.e("Exception", e.message, e)
           SearchResponse(listOf(),"0","true")
        }
    }

    override suspend fun getMovie(imdbId: String): Movie {
        return try {
            movieApi.getMovie(imdbId)
        }
        catch (e: Exception){
            Log.e("Exception", e.message, e)
            Movie()
        }
    }
}