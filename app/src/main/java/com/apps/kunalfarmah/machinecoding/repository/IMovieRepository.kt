package com.apps.kunalfarmah.machinecoding.repository

import com.apps.kunalfarmah.machinecoding.network.data.Movie
import com.apps.kunalfarmah.machinecoding.network.data.SearchResponse

interface IMovieRepository {
    suspend fun searchMovie(query: String): SearchResponse
    suspend fun getMovie(imdbId: String): Movie
}