package com.example.androidacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidacademy.data.Film


class MainActivity : AppCompatActivity(), FragmentMoviesDetailsClickListener, FragmentMoviesListClickListener {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, FragmentMoviesList()) // FragmentMoviesList()
                    .commit()
        }
    }

    override fun onOpenMovieDetail(film: Film) {
        findViewById<ConstraintLayout>(R.id.poster).apply {
            supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.main_container, FragmentMoviesDetails.newInstance(film))
                    .commit()
        }
    }

    override fun onOpenMoviesList() {
        findViewById<TextView>(R.id.back).apply {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, FragmentMoviesList())
                    .commit()
        }
    }
}

