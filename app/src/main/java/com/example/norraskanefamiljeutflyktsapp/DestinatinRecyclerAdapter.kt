package com.example.norraskanefamiljeutflyktsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DestinatinRecyclerAdapter (val context: Context,
                                 val destinations : List<Places>) : RecyclerView.Adapter<DestinatinRecyclerAdapter.ViewHolder>(){

 val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val destinationView = layoutInflater.inflate(R.layout.list_item_destination,parent,false)

        return ViewHolder(destinationView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
   val destination = destinations[position]
        holder.titleTextView.text = destination.title
        holder.descriptionTextView.text = destination.description
        holder.destinationImage.setImageResource(destination.destinationImage)
    }

    override fun getItemCount()= destinations.size

    inner class ViewHolder(destinationView: View) : RecyclerView.ViewHolder(destinationView){

        val titleTextView = destinationView.findViewById<TextView>(R.id.tv_titleRecyclerview)
        val descriptionTextView = destinationView.findViewById<TextView>(R.id.tv_ShortTextdescr)
        val destinationImage = destinationView.findViewById<ImageView>(R.id.imageView)
        val moreButton = destinationView.findViewById<Button>(R.id.button)


    }




}