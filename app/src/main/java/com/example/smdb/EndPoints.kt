package com.example.smdb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

interface EndPoints {
@GET("movie/popular")
    fun getMovies(@Query("api_key") key : String) : Call<Movies>

@GET("genre/movie/list")
    fun getGenres(@Query("api_key") key :String) : Call<Genres>

@GET("movie/now_playing")
    fun getNowPlaying(@Query("api_key") key:String) : Call<NowPlaying>

@GET("movie/top_rated")
    fun getTopRated(@Query("api_key") key: String): Call<TopRated>

@GET("movie/upcoming")
    fun getUpComing(@Query("api_key") key: String): Call<UpComing>

}
