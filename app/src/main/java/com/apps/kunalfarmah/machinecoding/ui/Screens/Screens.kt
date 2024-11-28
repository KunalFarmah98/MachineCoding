package com.apps.kunalfarmah.machinecoding.ui.Screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import com.apps.kunalfarmah.machinecoding.network.data.SearchedMovie
import com.apps.kunalfarmah.machinecoding.ui.destinations.Details
import com.apps.kunalfarmah.machinecoding.viewmodel.MovieViewModel


@Composable
fun Search(navController: NavController, viewModel: MovieViewModel, modifier: Modifier){
    var query by remember { mutableStateOf("") }
    var movies = viewModel.movies.collectAsStateWithLifecycle()
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = query, onValueChange = {value->
            query=value
            viewModel.searchMovie(query)
        })

        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            items(
                count = movies.value.data?.search?.size ?: 0,
                key = { index ->
                    movies.value.data?.search?.get(index)?.imdbID ?: ""
                }
            ) { index: Int ->
                MovieItem(navController, viewModel, movies.value.data?.search?.get(index))
            }

        }



    }
}

@Composable
fun MovieItem (navController: NavController, viewmodel:MovieViewModel, movie: SearchedMovie? = SearchedMovie("","","","")){
    val context = LocalContext.current
    val listener = object : ImageRequest.Listener{
        override fun onError(request: ImageRequest, result: ErrorResult) {
            super.onError(request, result)
            Log.e("Error", result.throwable.message?.toString() ?: "")
        }
    }
    val imageRequest = ImageRequest.Builder(context).diskCacheKey(movie?.poster).memoryCacheKey(movie?.poster).data(movie?.poster).listener(listener).build()
    Column(
        Modifier
            .padding(20.dp)
            .clickable {
                viewmodel.getMovie(movie?.imdbID?:"")
                navController.navigate(Details(movie?.imdbID?:""))
            }) {
        AsyncImage(model = imageRequest, contentDescription = movie?.title)
        Text(movie?.title?:"")
        Text(movie?.year?:"")
    }
}

@Composable
fun MovieDetails (movieViewModel: MovieViewModel, details: Details) {
    val context = LocalContext.current
    var id = details.id
    LaunchedEffect(true) {
        movieViewModel.getMovie(id)
    }
    val movieData = movieViewModel.movie.collectAsStateWithLifecycle()
    val movie = movieData.value.data
    val listener = object : ImageRequest.Listener {
        override fun onError(request: ImageRequest, result: ErrorResult) {
            super.onError(request, result)
            Log.e("Error", result.throwable.message?.toString() ?: "")
        }
    }
    val imageRequest =
        ImageRequest.Builder(context).diskCacheKey(movie?.poster).memoryCacheKey(movie?.poster)
            .data(movie?.poster).listener(listener).build()
    Column(
        Modifier
            .padding(20.dp))
             {
        AsyncImage(model = imageRequest, contentDescription = movie?.title)
        Text(movie?.title ?: "")
        Text(movie?.year ?: "")
        Text(movie?.runtime ?: "")
        Text(movie?.genre ?: "")
        Text(movie?.actors ?: "")
        Text(movie?.awards ?: "")
        Text(movie?.country ?: "")

    }
}