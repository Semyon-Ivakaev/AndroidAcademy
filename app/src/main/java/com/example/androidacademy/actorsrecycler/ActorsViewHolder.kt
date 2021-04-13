package com.example.androidacademy.actorsrecycler

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidacademy.R
import com.example.androidacademy.data.Actor
import com.example.androidacademy.data.Film
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ActorsViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val actorPhoto: ImageView = view.findViewById(R.id.first_image)
    private val actorName: TextView = view.findViewById(R.id.first_image_name)

    fun bind(element: Actor) {
        loadPhotoImage(element.actorPhoto)
        actorName.text = element.name
    }

    private fun loadPhotoImage(url: String) {
        Glide.with(itemView.context)
                .load(url)
                .placeholder(R.drawable.movie1)
                .into(actorPhoto)
    }
}