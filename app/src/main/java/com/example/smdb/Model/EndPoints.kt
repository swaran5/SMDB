package com.example.smdb.Model

import com.example.smdb.Genres
import com.example.smdb.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoints {
    @GET("movie/{name}")
    fun getMovies(
        @Path("name") name: String,
        @Query("api_key") key: String
    ): Call<Movies>

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") key: String): Call<Genres>

}
