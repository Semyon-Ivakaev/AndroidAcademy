package com.example.androidacademy.data

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.androidacademy.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Parcelable(private var film: Film, private val view: View) {

    private val backdrop_path: ImageView = view.findViewById(R.id.image)
    private var filmAge: TextView = view.findViewById(R.id.filmAge)
    private var textName: TextView = view.findViewById(R.id.textName)
    private var tag: TextView = view.findViewById(R.id.tag)
    private var ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
    private var reviews: TextView = view.findViewById(R.id.review_count)
    private var overview: TextView = view.findViewById(R.id.overview)

    fun createFilmDetail() {
        loadPosterImage(film.backdrop_path)
        filmAge.text = "${film.filmAge}+"
        textName.text = film.name
        tag.text = film.genre_ids.toString().replace("[", "").replace("]", "")
        ratingBar.rating = (film.like / 2).toFloat()
        reviews.text = "${film.reviewCount} REVIEWS"
        overview.text = film.overview

    }

    private fun loadPosterImage(url: String) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.movie1)
            .into(backdrop_path)
    }
}