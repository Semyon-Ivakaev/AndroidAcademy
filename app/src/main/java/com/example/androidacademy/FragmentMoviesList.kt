package com.example.androidacademy

import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademy.data.DataFilms
import com.example.androidacademy.data.Film
import com.example.androidacademy.filmsrecycler.AllFilmsAdapter
import com.example.androidacademy.filmsrecycler.OnClickListener
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.FileNotFoundException

open class FragmentMoviesList: Fragment() {

    private val handlerException = CoroutineExceptionHandler {
        coroutineContext, throwable ->
            Log.d("HandlerException", "exception handler: ${throwable.message}")
    }

    private val coroutineIO = CoroutineScope(Dispatchers.IO + Job() + handlerException)
    private val coroutineDefault = CoroutineScope(Dispatchers.Default + Job() + handlerException)

    private var fragmentMoviesDetailsClickListener: FragmentMoviesDetailsClickListener? = null

    private var films: MutableList<Film>? = null

    private var fileText: String? = null
    var genresText: String? = null


    private var allFilmsRecycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        // В отдельном потоке считываем JSON file в одну строку
        coroutineIO.launch {
            try {
                fileText = context?.assets?.open("data.json")?.bufferedReader().use {
                    it?.readText()
                }
                coroutineIO.launch {
                    genresText = context?.assets?.open("genres.json")?.bufferedReader().use {
                        it?.readText().toString()
                    }
                }
            } catch (ex: FileNotFoundException) {
                Log.v("AppVerbose", ex.toString())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        films = fileText?.let { DataFilms().getFilmsList(it, genresText.toString()) }

        Log.v("AppVerbose", "OnViewCreated start")
        allFilmsRecycler = view.findViewById(R.id.allFilms_rv)
        // обрабатываем на клик элемента RV
        allFilmsRecycler?.adapter = films?.let { AllFilmsAdapter(it, object : OnClickListener{
            override fun onVariantClick(film: Film) {
                fragmentMoviesDetailsClickListener?.onOpenMovieDetail(film)
            }
        }) }
        allFilmsRecycler?.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesDetailsClickListener) {
            fragmentMoviesDetailsClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMoviesDetailsClickListener = null
    }
}