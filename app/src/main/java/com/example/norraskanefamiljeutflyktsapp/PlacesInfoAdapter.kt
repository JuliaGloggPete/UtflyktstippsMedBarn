package com.example.norraskanefamiljeutflyktsapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class PlacesInfoAdapter(val context: Context): GoogleMap.InfoWindowAdapter {


   val layoutInflater = LayoutInflater.from(context
   )


    override fun getInfoContents(p0: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
   val infoWindow = layoutInflater.inflate(R.layout.info_window, null)
        val imageView = infoWindow.findViewById<ImageView>(R.id.iv_infoWondow)
        val titleView = infoWindow.findViewById<TextView>(R.id.tv_infoWindow_Title)
        val infoView = infoWindow.findViewById<TextView>(R.id.tv_InfoWindow_adress)

        val destination = marker.tag as? Places

        titleView.text = destination?.title
        infoView.text = destination?.PostalCodeNVillage

        if (destination != null){



            if(destination.destinationImagePath.isNotEmpty()) {


                val imageRef = Firebase.storage.reference.child(destination.destinationImagePath)
                imageRef.downloadUrl.addOnSuccessListener { Uri ->
                    val imageUrl = Uri.toString()
                    Log.d("Image URL", imageUrl)
                    Log.d("Image Data", destination.destinationImage.toString())

                    Glide.with(context)
                        .load(imageUrl)
                        .into(imageView)
                }

            }           else
            {
                destination.destinationImage?.let { imageView.setImageResource(it) }}

        }









return  infoWindow

    }


}