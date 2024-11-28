package com.apps.kunalfarmah.machinecoding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.kunalfarmah.machinecoding.network.data.DataOrLoading
import com.apps.kunalfarmah.machinecoding.network.data.Movie
import com.apps.kunalfarmah.machinecoding.network.data.SearchResponse
import com.apps.kunalfarmah.machinecoding.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: IMovieRepository):ViewModel() {
    val _movies = MutableStateFlow<DataOrLoading<SearchResponse,Boolean>>(DataOrLoading(null,true))
    val movies = _movies.asStateFlow()

    val _movie = MutableStateFlow<DataOrLoading<Movie,Boolean>>(DataOrLoading(null, true))
    val movie = _movie.asStateFlow()

    fun searchMovie(query: String){
        viewModelScope.launch {
            _movies.value = DataOrLoading(movieRepository.searchMovie(query),false)
        }
    }

    // a b c
    // a b c c
    // a c b c

    // a b
    // c
    // d
    // c


    fun getMovie(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.getMovie(id)
            _movie.value = DataOrLoading(movie,false)
        }
    }
}