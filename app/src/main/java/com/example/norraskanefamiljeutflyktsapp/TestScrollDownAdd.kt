package com.example.norraskanefamiljeutflyktsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TestScrollDownAdd : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var title : EditText
    lateinit var street : EditText
    lateinit var postalCodeNCity : EditText
    lateinit var description : EditText
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
    lateinit var price : EditText
    lateinit var openHours : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_scroll_down_add)

        db = Firebase.firestore

        title = findViewById(R.id.et_title)
        street = findViewById(R.id.et_street)
        postalCodeNCity = findViewById(R.id.et_postalCodeNCity)
        description = findViewById(R.id.et_descritption)
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
        price = findViewById(R.id.et_price)
        openHours = findViewById(R.id.et_OpenHours)









        val spinner: Spinner = findViewById(R.id.spinner_age)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.age_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        // Key classes are the following:

        //Spinner
        // SpinnerAdapter
        //AdapterView.OnItemSelectedListener
        // https://developer.android.com/develop/ui/views/components/spinner


    }

    fun saveDestination(){
        //val destination =


    }

}