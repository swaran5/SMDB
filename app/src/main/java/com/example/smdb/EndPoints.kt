package com.example.smdb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

interface EndPoints {
    @GET("movie/{name}")
    fun getMovies(
        @Path("name") name: String,
        @Query("api_key") key: String
    ): Call<Movies>

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") key: String): Call<Genres>

}
