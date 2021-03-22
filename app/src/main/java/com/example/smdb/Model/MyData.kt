package com.example.smdb

data class Movies(
    val results: List<Result>
)

data class Result(
    val id: Int,
    var title: String,
    val adult: Boolean,
    val poster_path: String,
    val release_date: String,
    val original_language: String,
    val backdrop_path: String,
    val overview: String,
    val genre_ids: Array<String>
)

data class Genres(
    val genres: List<GenreName>
)

data class GenreName(
    val id: Int,
    val name: String
)

