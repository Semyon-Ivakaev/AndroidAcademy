package com.example.androidacademy.filmsrecycler

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.androidacademy.R
import com.example.androidacademy.data.Film

class AllFilmsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val posterImage: ImageView = itemView.findViewById(R.id.poster_image)
    private val filmAge: TextView = itemView.findViewById(R.id.filmAge)
    private val tag: TextView = itemView.findViewById(R.id.tag)
    private val reviewCount: TextView = itemView.findViewById(R.id.review_count)
    private val filmName: TextView = itemView.findViewById(R.id.textName)
    private val filmTime: TextView = itemView.findViewById(R.id.min)
    private val stars: RatingBar = itemView.findViewById(R.id.ratingBar)
    private val like: ImageView = itemView.findViewById(R.id.like)


    fun bind(element: Film) {
        Log.v("AppVerbose", "Cобираем превью фильма $element")

        loadPosterImage(element.posterImage)
        filmName.text = element.name
        filmAge.text = "${element.filmAge}+"
        filmTime.text = "${element.length} MIN"
        reviewCount.text = "${element.reviewCount} REVIEWS"
        stars.rating = (element.like / 2).toFloat()
        // Массив переделали в строку, убрали скобки
        tag.text = element.genre_ids.toString().replace("[", "").replace("]", "")
    }

    private fun loadPosterImage(url: String) {
        Glide.with(itemView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE) // кэширование
            .into(posterImage)
    }
}