package com.example.norraskanefamiljeutflyktsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.norraskanefamiljeutflyktsapp.DataManager.destinations

class DestinatinRecyclerAdapter (val context: Context,
                                 val destinations : List<Places>,
val listener: OnClickListener) : RecyclerView.Adapter<DestinatinRecyclerAdapter.ViewHolder>(){

 val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val destinationView = layoutInflater.inflate(R.layout.list_item_destination,parent,false)

        return ViewHolder(destinationView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
   val destination = destinations[position]
        holder.titleTextView.text = destination.title
        holder.descriptionTextView.text = destination.description
        destination.destinationImage?.let { holder.destinationImage.setImageResource(it) }
        holder.ageRecomendatin.text =destination.ageFrom
        if (destination.restaurant == true){
            holder.restaurantImage.setImageResource(R.drawable.ic_baseline_restaurant_menu_24)
        }

 if (destination.restaurant == true){
            holder.restaurantImage.setImageResource(R.drawable.ic_baseline_restaurant_menu_24)
        } else {holder.restaurantImage.setImageResource(R.drawable.ic_baseline_restaurant_menu_grey)}

 if (destination.accesStroller == true){
            holder.strolerImage.setImageResource(R.drawable.ic_baseline_child_friendly_24)
        }else {holder.strolerImage.setImageResource(R.drawable.ic_baseline_child_friendly_grey)}

 if (destination.accesDisability == true){
            holder.wheelChairImage.setImageResource(R.drawable.ic_baseline_accessible_24)
        } else{
     holder.wheelChairImage.setImageResource(R.drawable.ic_baseline_accessible_grey)
 }

 if (destination.bbqplace == true){
            holder.BBQImage.setImageResource(R.drawable.ic_baseline_outdoor_grill_24)
        } else {
     holder.BBQImage.setImageResource(R.drawable.ic_baseline_outdoor_grill_grey)
 }

        if (destination.playgroundNearby == true){
            holder.playgroundImage.setImageResource(R.drawable.ic_baseline_toys_24)
        }else {
            holder.playgroundImage.setImageResource(R.drawable.ic_baseline_toys_grey)
        }

 if (destination.animalstosee == true){
            holder.animalsImage.setImageResource(R.drawable.ic_baseline_pets_24)
        }else{
     holder.animalsImage.setImageResource(R.drawable.ic_baseline_pets_grey)
    }

 if (destination.shop == true){
            holder.shopImage.setImageResource(R.drawable.ic_baseline_shopping_cart_24)
        } else {
     holder.shopImage.setImageResource(R.drawable.ic_baseline_shopping_cart_grey)
 }

if (destination.indoorActivity== true){
            holder.indoorImage.setImageResource(R.drawable.ic_baseline_home_24)
        } else {
    holder.indoorImage.setImageResource(R.drawable.ic_baseline_home_grey)
}


    }



    override fun getItemCount()= destinations.size

    inner class ViewHolder(destinationView: View) : RecyclerView.ViewHolder(destinationView){

        val titleTextView = destinationView.findViewById<TextView>(R.id.tv_titleRecyclerview)
        val descriptionTextView = destinationView.findViewById<TextView>(R.id.tv_ShortTextdescr)
        val destinationImage = destinationView.findViewById<ImageView>(R.id.imageView)
        val restaurantImage = destinationView.findViewById<ImageView>(R.id.iv_attribute1)
        val strolerImage = destinationView.findViewById<ImageView>(R.id.iv_attribute2)
        val wheelChairImage = destinationView.findViewById<ImageView>(R.id.iv_attribute3)
        val BBQImage = destinationView.findViewById<ImageView>(R.id.iv_attribute4)
        val playgroundImage = destinationView.findViewById<ImageView>(R.id.iv_attribute5)
        val animalsImage = destinationView.findViewById<ImageView>(R.id.iv_attribute6)
        val shopImage = destinationView.findViewById<ImageView>(R.id.iv_attribute7)
        val indoorImage = destinationView.findViewById<ImageView>(R.id.iv_attribute8)
        val ageRecomendatin = destinationView.findViewById<TextView>(R.id.txtv_rclv_agerec)

        init {
            itemView.setOnClickListener{
                val postion = adapterPosition
                listener.OnClick(postion)


            }

        }



    }

    interface OnClickListener{
        fun OnClick(position: Int)
       // https://www.youtube.com/watch?v=nvmqVN7kJ_Q

    }




}