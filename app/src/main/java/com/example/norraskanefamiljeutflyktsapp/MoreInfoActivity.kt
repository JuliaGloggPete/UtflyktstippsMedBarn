package com.example.norraskanefamiljeutflyktsapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MoreInfoActivity : AppCompatActivity() {


    lateinit var titleView: TextView
    lateinit var descriptionView: TextView
    lateinit var priceView: TextView
    lateinit var durationView: TextView
    lateinit var adressView: TextView
    lateinit var homepageView: TextView
    lateinit var attriebutesView: TextView

    lateinit var destinationImage: ImageView
    lateinit var restaurant: String
    lateinit var stroler: String
    lateinit var handicap: String
    lateinit var bbq: String
    lateinit var playground: String
    lateinit var animalsToSee: String
    lateinit var shopNearby: String
    lateinit var indoor: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)


        val id = intent.getStringExtra("id")


        titleView = findViewById<TextView>(R.id.tv_moreInfo_title)
        descriptionView = findViewById(R.id.tv_description_moreInfo)
        priceView = findViewById(R.id.tv_price_MoreInfo)
        durationView = findViewById(R.id.tv_duration_MoreInfo)
        adressView = findViewById(R.id.tv_Adress_moreInfo)
        homepageView = findViewById(R.id.tv_webside_moreInfo)
        destinationImage = findViewById(R.id.iv_MoreInfo)
        attriebutesView = findViewById(R.id.tv_MoreInfo_Attributes)

        val backButton = findViewById<Button>(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }

        restaurant = "nej/ej angiven"
        stroler = "nej/ej angiven"
        handicap = "nej/ej angiven"
        bbq = "nej/ej angiven"
        playground = "nej/ej angiven"
        animalsToSee = "nej/ej angiven"
        shopNearby = "nej/ej angiven"
        indoor = "nej/ej angiven"


        for (destination in DataManager.destinations) {
            if (id == destination.documentId) {
                titleView.text = destination.title
                descriptionView.text = destination.description

                if (destination.price!!.isNotEmpty()) {
                    priceView.text = "Pris: " + destination.price
                } else {
                    priceView.text = ""
                }

                if (destination.adressStreetName!!.isNotEmpty()
                    && destination.PostalCodeNVillage!!.isNotEmpty()
                ) {
                    adressView.text =
                        "Adress: " + destination.adressStreetName + ", " + destination.PostalCodeNVillage
                } else {
                    adressView.text = ""
                }

                durationView.text = "Uttflktslängt: " + destination.duration


                if (destination.homepage!!.isNotEmpty()) {
                    homepageView.text = "Hemsida: " + destination.homepage
                }


                if (destination.restaurant == true) {
                    restaurant = "ja"
                }

                if (destination.accesStroller == true) {
                    stroler = "ja"
                }

                if (destination.accesDisability == true) {
                    handicap = "ja"
                }


                if (destination.bbqplace == true) {
                    bbq = "ja"
                }

                if (destination.playgroundNearby == true) {
                    playground = "ja"
                }

                if (destination.animalstosee == true) {
                    animalsToSee = "ja"
                }

                if (destination.shop == true) {
                    shopNearby = "ja"
                }

                if (destination.indoorActivity == true) {
                    indoor = "ja"
                }



                attriebutesView.text =
                    "Restaurant/Bistro på plats: ${restaurant} \ntillgängligt med barnvagn: ${stroler}\nbra tillgänglig med rullstol/service för rullstolförare: ${handicap}\n" +
                            "grillplats: ${bbq}\nlekplats i närheten: ${playground}\nfinns djur att se: ${animalsToSee}\nkiosk eller shop i närheten : ${shopNearby}\n" +
                            "utflykt är under tak/inomhus: ${indoor}  "


                if (destination.destinationImagePath.isNotEmpty()) {
                    val imageRef =
                        Firebase.storage.reference.child(destination.destinationImagePath)
                    imageRef.downloadUrl.addOnSuccessListener { Uri ->
                        val imageUrl = Uri.toString()

                        Glide.with(this)
                            .load(imageUrl)
                            .into(destinationImage)
                    }

                } else {
                    destination.destinationImage?.let { destinationImage.setImageResource(it) }
                }


            }
        }
    }


}

