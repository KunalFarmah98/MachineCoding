package com.apps.kunalfarmah.machinecoding.network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("Search")
    val search: List<SearchedMovie>?,
    @SerialName("totalResults")
    val totalResults: String,
    @SerialName("Response")
    val response: String
)

@Serializable
data class SearchedMovie(
    @SerialName("Title")
    val title: String?="",
    @SerialName("Year")
    val year: String?="",
    @SerialName("imdbID")
    val imdbID: String?="",
    @SerialName("Type")
    val type: String?="",
    @SerialName("Poster")
    val poster: String?="",
)