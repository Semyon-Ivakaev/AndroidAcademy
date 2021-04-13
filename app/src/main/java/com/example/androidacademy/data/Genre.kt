package com.example.androidacademy.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
)