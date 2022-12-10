package com.example.norraskanefamiljeutflyktsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TestScrollDownAdd : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var titleEditText : EditText
    lateinit var streetEditText : EditText
    lateinit var postalCodeNCityEditText : EditText
    lateinit var descriptionEditText : EditText
    lateinit var ageRecommendaiton : Spinner
    lateinit var checkBoxOnPlace: CheckBox
    lateinit var latLng: LatLng //!!!!
    lateinit var checkBoxRestaurant: CheckBox
    lateinit var checkBoxPlayground: CheckBox
    lateinit var checkBoxBBQPlace: CheckBox
    lateinit var checkBoxIndoor: CheckBox
    lateinit var checkBoxAnimalsToSee: CheckBox
    lateinit var checkBoxShop: CheckBox
    lateinit var checkBoxStrolerAccess: CheckBox
    lateinit var checkBoxHandicapAccess: CheckBox
    lateinit var durationOfActivity : Spinner
    lateinit var priceEditText: EditText
    lateinit var openHoursEditText : EditText
    lateinit var homepageEditText :EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_scroll_down_add)

        db = Firebase.firestore

        titleEditText = findViewById(R.id.et_title)
        streetEditText = findViewById(R.id.et_street)
        postalCodeNCityEditText = findViewById(R.id.et_postalCodeNCity)
        homepageEditText = findViewById(R.id.et_Homepage)
        descriptionEditText = findViewById(R.id.et_descritption)
        ageRecommendaiton = findViewById<Spinner>(R.id.spinner_age)
        checkBoxOnPlace = findViewById<CheckBox>(R.id.checkBox_onPlace)
        checkBoxRestaurant = findViewById<CheckBox>(R.id.checkB_Rest_Bistro)
        checkBoxPlayground  = findViewById<CheckBox>(R.id.checkB_playgr)
        checkBoxBBQPlace =  findViewById<CheckBox>(R.id.checkB_bbqPlace)
        checkBoxIndoor = findViewById<CheckBox>(R.id.checkB_indoor)
        checkBoxAnimalsToSee = findViewById<CheckBox>(R.id.checkB_Animals)
        checkBoxShop = findViewById<CheckBox>(R.id.checkB_store)
        checkBoxStrolerAccess = findViewById<CheckBox>(R.id.checkB_strollerAcces)
        checkBoxHandicapAccess = findViewById<CheckBox>(R.id.checkB_handicapAcces)
        durationOfActivity = findViewById<Spinner>(R.id.spinner_duration)
        priceEditText = findViewById(R.id.et_price)
        openHoursEditText = findViewById(R.id.et_OpenHours)

        val addButton = findViewById<Button>(R.id.btn_add)

        addButton.setOnClickListener {
           saveDestination()
        }










        val spinner: Spinner = findViewById(R.id.spinner_age)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,

            R.array.age_array,
            android.R.layout.simple_spinner_item
        ).also { spinnerAdapter ->
            // Specify the layout to use when the list of choices appears
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = spinnerAdapter
        }

        // Key classes are the following:

        //Spinner
        // SpinnerAdapter
        //AdapterView.OnItemSelectedListener
        // https://developer.android.com/develop/ui/views/components/spinner


    }

 fun saveDestination(){
        var title  = titleEditText.text.toString()
        var adressStreetName = streetEditText.text.toString()
        var postalCodeNVillage= postalCodeNCityEditText.text.toString()
        var homepage = homepageEditText.text.toString()

        var description = descriptionEditText.text.toString()
        var latlang = LatLng(40.8,40.9)
     var restaurant  = false
     var accesDisability = false

     if (checkBoxHandicapAccess.isChecked){
          accesDisability = true
     }

     if (checkBoxRestaurant.isChecked){
          restaurant  = true
     }

     var  bbqplace = false

     if (checkBoxBBQPlace.isChecked){
         bbqplace = true

     }
     var shop = false
     if (checkBoxShop.isChecked){
         shop = true

     }

     var playgroundNearby =false
     if(checkBoxPlayground.isChecked){
          playgroundNearby = true
    }
     var animalstosee = false

     if (checkBoxAnimalsToSee.isChecked){

      animalstosee = true
     }
     var accesStroller = false
     if (checkBoxStrolerAccess.isChecked){

        accesStroller = true
     }
     var indoor = false
     if (checkBoxIndoor.isChecked){

         indoor = true
     }

            var extraPlaceholder = false




        var duration = durationOfActivity.scrollX.toString()
        var ageFrom = ageRecommendaiton.scrollX.toString()

        var price = priceEditText.text.toString()
        var openinghours= openHoursEditText.text.toString()
        var destinationImage = R.drawable.example_picture



        val destination = Places(title,adressStreetName,
            postalCodeNVillage,homepage,
        description,latlang,restaurant,playgroundNearby,
            bbqplace,animalstosee,shop,
            accesDisability,accesStroller,indoor,false,duration,ageFrom,price,openinghours,destinationImage
        )
     DataManager.destinations.add(destination)
     finish()

     //glöm inte ta bort de svenska texter



    }

}