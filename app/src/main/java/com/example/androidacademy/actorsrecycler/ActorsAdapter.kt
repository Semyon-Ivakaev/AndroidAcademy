package com.example.androidacademy.actorsrecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademy.R
import com.example.androidacademy.data.Actor

class ActorsAdapter(context: Context, list: MutableList<Actor>):RecyclerView.Adapter<ActorsViewHolder>() {
    private val actorList = list
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(inflater.inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    fun getItem(position: Int) = actorList[position]
}