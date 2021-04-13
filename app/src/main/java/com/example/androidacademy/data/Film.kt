package com.example.androidacademy.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Film(
    @Json(name = "title")
    val name: String,
    @Json(name = "original_title")
    val tag: String,
    val filmAge: String = "12",
    @Json(name = "runtime")
    val length: String,
    @Json(name = "vote_count")
    val reviewCount: Int,
    @Json(name = "poster_path")
    val posterImage: String,
    @Json(name = "vote_average")
    val like: Double,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdrop_path: String,
    @Json(name = "original_language")
    val original_language: String,
    @Json(name = "genre_ids")
    var genre_ids: List<String>,
    @Json(name = "actors")
    var actors: List<Int>,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "release_date")
    val release_date: String,
    ) : Serializable

data class ActorsInFilm(var actorsInFilm: List<Actor>)



