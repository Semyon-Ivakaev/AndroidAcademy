package com.example.androidacademy.data

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

open class DataFilms {
    open fun getFilmsList(dataJson: String, genresJson: String): MutableList<Film> {
        val listOfFilms = mutableListOf<Film>()

        val dataJsonList = dataJson.split("},")
        val listOfGenres = readGenresJson(genresJson)

        for (i in dataJsonList.indices) {
            var element = dataJsonList.get(i)
            if (i == dataJsonList.size - 1) {
                element = element.substring(0, element.length - 4)
            }
            listOfGenres?.let { readJson(element, it)?.let { listOfFilms.add(it) } }
        }

        return listOfFilms
    }

    private fun readJson(films: String, listOfGenres: List<Genre>): Film? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<Film> = moshi.adapter(Film::class.java)
        // substring нужен чтобы убрать лишний сивол "[" а добавление "}" так как спличу по этому
        // символу и он удаляется, а без него jsonAdapter не работает, нужен формат { json }
        val film = jsonAdapter.fromJson(films.substring(1) + "}")
        Log.v("AppVerbose", film.toString())

        // Фильму теперь поменяем массив из цифр на массив Строк
        film?.genre_ids = getGenresToFilm(film, listOfGenres)
        return film
    }

    // Метод для замены массива целых чисел на массив строк
    private fun getGenresToFilm(film: Film?, genresList: List<Genre>): List<String> {
        Log.v("AppVerbose", "Result: getGenresToFilm")
        val genresListWithString = mutableListOf<String>()
        val ids = film?.genre_ids as List<String>

        for (el in genresList) {
            if (el.id in ids) {
                genresListWithString.add(el.name)
            }
        }
        Log.v("AppVerbose", "$genresListWithString")
        return genresListWithString
    }

    private fun readGenresJson(dataJson: String): List<Genre>? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        // Распарсим в массив объектов
        var type = Types.newParameterizedType(MutableList::class.java, Genre::class.java)
        var adapter: JsonAdapter<List<Genre>> = moshi.adapter(type)
        var genres: List<Genre>? = adapter.fromJson(dataJson)
        Log.v("AppVerbose", "Result: ${genres.toString()}")

        return genres
    }
}