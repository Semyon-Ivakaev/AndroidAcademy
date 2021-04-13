package com.example.androidacademy.filmsrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademy.R
import com.example.androidacademy.data.Film

class AllFilmsAdapter(filmsList: MutableList<Film>, private val onClickListener: OnClickListener): RecyclerView.Adapter<AllFilmsViewHolder>() {

    private val films = filmsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllFilmsViewHolder {

        return AllFilmsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllFilmsViewHolder, position: Int) {
        val film = films[position]
        holder.itemView.setOnClickListener {
            onClickListener.onVariantClick(film)
        }
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return films.size
    }

    private fun getItem(position: Int): Film {
        return films[position]
    }
}

interface OnClickListener {
    fun onVariantClick(film: Film)
}
