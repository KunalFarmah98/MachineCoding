package com.apps.kunalfarmah.machinecoding.network.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.apps.kunalfarmah.machinecoding.ui.Screens.Search
import com.apps.kunalfarmah.machinecoding.ui.Screens.MovieDetails
import com.apps.kunalfarmah.machinecoding.ui.destinations.Details
import com.apps.kunalfarmah.machinecoding.ui.destinations.Home
import com.apps.kunalfarmah.machinecoding.viewmodel.MovieViewModel

@Composable
fun NavigationHost(viewModel: MovieViewModel, modifier: Modifier){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Home) {
        composable<Home> { Search(navController, viewModel, modifier) }
        composable<Details> { MovieDetails(viewModel, it.toRoute<Details>()) }
    }
}