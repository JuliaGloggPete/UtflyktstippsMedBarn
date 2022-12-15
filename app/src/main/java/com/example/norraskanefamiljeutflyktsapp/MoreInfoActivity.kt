package com.example.norraskanefamiljeutflyktsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MoreInfoActivity : AppCompatActivity() {


lateinit var titleView : TextView
lateinit var descriptionView : TextView
lateinit var priceView : TextView
lateinit var durationView : TextView
lateinit var adressView : TextView
lateinit var homepageView : TextView
lateinit var attributesView : TextView
lateinit var destinationImage : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        val id = intent.getStringExtra("id")


        titleView = findViewById<TextView>(R.id.tv_moreInfo_title)
        descriptionView = findViewById(R.id.tv_description_moreInfo)
        priceView = findViewById(R.id.tv_price_MoreInfo)
        durationView =findViewById(R.id.tv_duration_MoreInfo)
        adressView = findViewById(R.id.tv_Adress_moreInfo)
        homepageView = findViewById(R.id.tv_webside_moreInfo)
        destinationImage = findViewById(R.id.iv_MoreInfo)

        attributesView = findViewById(R.id.tv_attributes_moreInfe)
       val backButton = findViewById<Button>(R.id.btn_back)
        backButton.setOnClickListener{
            finish()
        }



        for (destination in DataManager.destinations) {
            if (id == destination.documentId)

            {
                titleView.text =destination.title
                descriptionView.text =destination.description
                priceView.text = "Pris: "+destination.price
                durationView.text = "Uttflktslängt: "+destination.duration
                adressView.text= "Adress: "+destination.adressStreetName+" "+destination.PostalCodeNVillage
                homepageView.text = "Hemsida: "+destination.homepage

                //attributesView.text = destination.playgroundNearby.toString()
                destination.destinationImage?.let { destinationImage.setImageResource(it) }

            }
        }










    }
}