package com.apps.kunalfarmah.machinecoding.network.di

import com.apps.kunalfarmah.machinecoding.Constants
import com.apps.kunalfarmah.machinecoding.network.api.MovieApi
import com.apps.kunalfarmah.machinecoding.repository.IMovieRepository
import com.apps.kunalfarmah.machinecoding.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesClient():OkHttpClient = OkHttpClient.Builder().readTimeout(15,TimeUnit.SECONDS).connectTimeout(10,TimeUnit.SECONDS).build()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder().client(client).addConverterFactory(Json{ignoreUnknownKeys=true}.asConverterFactory("application/json".toMediaType())).baseUrl(Constants.BASE_URL).build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)

    @Provides
    fun provideMovieRepository(api: MovieApi) = MovieRepository(api) as IMovieRepository
}