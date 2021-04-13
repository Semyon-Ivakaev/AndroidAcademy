package com.example.androidacademy

import com.example.androidacademy.data.Film

interface FragmentMoviesDetailsClickListener {
    fun onOpenMovieDetail(film: Film)
}