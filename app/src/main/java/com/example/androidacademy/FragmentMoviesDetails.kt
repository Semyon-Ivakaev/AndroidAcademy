package com.example.androidacademy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademy.actorsrecycler.ActorsAdapter
import com.example.androidacademy.data.Actor
import com.example.androidacademy.data.DataActors
import com.example.androidacademy.data.Film
import com.example.androidacademy.data.Parcelable
import kotlinx.coroutines.*

class FragmentMoviesDetails: Fragment() {

    private val handlerException = CoroutineExceptionHandler {
        coroutineContext, throwable ->
        Log.d("HandlerException", "exception handler: ${throwable.message}")
    }

    private var fragmentMoviesListClickListener: FragmentMoviesListClickListener? = null
    val coroutineIO = CoroutineScope(Dispatchers.IO + Job() + handlerException)

    private var film: Film? = null
    private var peopleText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        coroutineIO.launch {
            peopleText = context?.assets?.open("people.json")?.bufferedReader().use {
                it?.readText()
            }
            Log.v("AppVerbose", "$peopleText")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        film = arguments?.getSerializable("FILM") as Film?
        // Парсим фильм и собираем фрагмент
        film?.let { Parcelable(it, view).createFilmDetail() }

        Log.v("AppVerbose", film.toString())
        view.findViewById<TextView>(R.id.back).apply {
            setOnClickListener {
                fragmentMoviesListClickListener?.onOpenMoviesList()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // передаем список актеров из DataActors returnListActors() в RecyclerView
        val listActor = DataActors(film).returnListActors(peopleText)

        val recycler:RecyclerView = view.findViewById(R.id.actors_rv)
        recycler.adapter = ActorsAdapter(view.context, listActor)
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesListClickListener) {
            fragmentMoviesListClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMoviesListClickListener = null
    }

    companion object {
        fun newInstance(film: Film):FragmentMoviesDetails {
            val args = Bundle()
            args.putSerializable("FILM", film)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
    }
}