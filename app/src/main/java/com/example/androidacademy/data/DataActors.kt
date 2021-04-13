package com.example.androidacademy.data

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DataActors(val film: Film?) {
    fun returnListActors(dataJson: String?): MutableList<Actor> {
        return addActorsToFilm(readPeopleJson(dataJson)) as MutableList<Actor>
    }
    private fun readPeopleJson(dataJson: String?): List<Actor>? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(MutableList::class.java, Actor::class.java)
        var adapter: JsonAdapter<List<Actor>> = moshi.adapter(type)
        var actors = adapter.fromJson(dataJson)
        Log.v("AppVerbose", "$actors")

        return actors
    }

    private fun addActorsToFilm(actors: List<Actor>?): List<Actor> {
        val actorsName = mutableListOf<Actor>()
        val ids = film?.actors

        if (actors != null) {
            for (el in actors) {
                if (ids != null) {
                    if (el.id in ids) {
                        actorsName.add(el)
                    }
                }
            }
        }
        return actorsName
    }
}