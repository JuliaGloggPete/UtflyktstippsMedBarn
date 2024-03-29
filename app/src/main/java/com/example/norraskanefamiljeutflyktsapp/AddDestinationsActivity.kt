package com.example.norraskanefamiljeutflyktsapp

import android.Manifest
import android.Manifest.permission
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class AddDestinationsActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore

    lateinit var titleEditText: EditText
    lateinit var streetEditText: EditText
    lateinit var postalCodeNCityEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var priceEditText: EditText
    lateinit var openHoursEditText: EditText
    lateinit var homepageEditText: EditText

    lateinit var durationOfActivity: String
    lateinit var ageRecommendaiton: String

    lateinit var checkBoxOnPlace: CheckBox
    lateinit var checkBoxRestaurant: CheckBox
    lateinit var checkBoxPlayground: CheckBox
    lateinit var checkBoxBBQPlace: CheckBox
    lateinit var checkBoxIndoor: CheckBox
    lateinit var checkBoxAnimalsToSee: CheckBox
    lateinit var checkBoxShop: CheckBox
    lateinit var checkBoxStrolerAccess: CheckBox
    lateinit var checkBoxHandicapAccess: CheckBox


    lateinit var takeInPick: ImageView
    lateinit var imageUploadBtn: Button


    lateinit var locationProvider: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    private val REQUEST_LOCATION = 1


    var longitude: Double? = null
    var latitude: Double? = null

    lateinit var destinationPath: String

    lateinit var imageUri: Uri
    lateinit var imageFileName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        db = Firebase.firestore

        titleEditText = findViewById(R.id.et_title)
        streetEditText = findViewById(R.id.et_street)
        postalCodeNCityEditText = findViewById(R.id.et_postalCodeNCity)
        homepageEditText = findViewById(R.id.et_Homepage)
        descriptionEditText = findViewById(R.id.et_descritption)
        checkBoxOnPlace = findViewById<CheckBox>(R.id.checkBox_onPlace)
        checkBoxRestaurant = findViewById<CheckBox>(R.id.checkB_Rest_Bistro)
        checkBoxPlayground = findViewById<CheckBox>(R.id.checkB_playgr)
        checkBoxBBQPlace = findViewById<CheckBox>(R.id.checkB_bbqPlace)
        checkBoxIndoor = findViewById<CheckBox>(R.id.checkB_indoor)
        checkBoxAnimalsToSee = findViewById<CheckBox>(R.id.checkB_Animals)
        checkBoxShop = findViewById<CheckBox>(R.id.checkB_store)
        checkBoxStrolerAccess = findViewById<CheckBox>(R.id.checkB_strollerAcces)
        checkBoxHandicapAccess = findViewById<CheckBox>(R.id.checkB_handicapAcces)
        priceEditText = findViewById(R.id.et_price)
        openHoursEditText = findViewById(R.id.et_OpenHours)
        destinationPath = "" //init to avoid crash

        val addButton = findViewById<Button>(R.id.btn_add)
        addButton.setOnClickListener {
            saveDestination()
        }

        val cancelButton = findViewById<Button>(R.id.btn_cancel)
        cancelButton.setOnClickListener { finish() }

        imageUploadBtn = findViewById<Button>(R.id.btn_goToUploadActivity)

        imageUploadBtn.setOnClickListener {
            selectImage()
        }

        takeInPick = findViewById<ImageView>(R.id.iv_uploadIamge)

        locationProvider = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.Builder(2000).build()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationsResult: LocationResult) {
                for (location in locationsResult.locations) {

                    latitude = location.latitude
                    longitude = location.longitude
                }

            }

        }
        if (ActivityCompat.checkSelfPermission(
                this,
                permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )
        }


        checkBoxOnPlace.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(
                        arrayOf(permission.ACCESS_FINE_LOCATION),
                        REQUEST_LOCATION
                    )

                } else {
                    locationProvider.requestLocationUpdates(locationRequest, locationCallback, null)
                }
            } else {
                locationProvider.removeLocationUpdates(locationCallback)
            }
        }

        val duration = arrayOf(
            "ej angiven",
            "ca 30 min", "1-2 timmar", "halvdagsutflykt",
            "heldagsutflykt", "heldagsutflykt med övernattningsmöjlighet"
        )

        val durationSpinner = findViewById<Spinner>(R.id.spinner_duration)

        val durationArrayAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, duration
        )
        durationSpinner.adapter = durationArrayAdapter
        durationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                durationOfActivity = duration[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val ageRec = arrayOf(
            "alla ålder", "från 1", "från 2", "från 3", "från 4", "från 5",
            "från 6", "från 7", "från 8", "från 9"
        )

        val agespinner = findViewById<Spinner>(R.id.spinner_age)
        val ageArrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            ageRec
        )
        agespinner.adapter = ageArrayAdapter
        agespinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                ageRecommendaiton = ageRec[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }

    fun saveDestination() {


        var title = titleEditText.text.toString()
        var adressStreetName = streetEditText.text.toString()
        var postalCodeNVillage = postalCodeNCityEditText.text.toString()
        var homepage = homepageEditText.text.toString()
        var description = descriptionEditText.text.toString()
        var restaurant = false
        var accesDisability = false

        if (checkBoxOnPlace.isChecked) {
            latitude
            longitude

        } else {

            latitude == null
            longitude == null

        }

        if (checkBoxHandicapAccess.isChecked) {
            accesDisability = true
        }

        if (checkBoxRestaurant.isChecked) {
            restaurant = true
        }

        var bbqplace = false

        if (checkBoxBBQPlace.isChecked) {
            bbqplace = true

        }
        var shop = false
        if (checkBoxShop.isChecked) {
            shop = true

        }

        var playgroundNearby = false
        if (checkBoxPlayground.isChecked) {
            playgroundNearby = true
        }
        var animalstosee = false

        if (checkBoxAnimalsToSee.isChecked) {

            animalstosee = true
        }
        var accesStroller = false
        if (checkBoxStrolerAccess.isChecked) {

            accesStroller = true
        }
        var indoor = false
        if (checkBoxIndoor.isChecked) {

            indoor = true
        }

        var duration = durationOfActivity
        var ageFrom = ageRecommendaiton

        var price = priceEditText.text.toString()
        var openinghours = openHoursEditText.text.toString()
        var destinationImage = R.drawable.example_picture


        val destination = Places(
            title, adressStreetName,
            postalCodeNVillage, homepage,
            description,
            latitude, longitude,
            restaurant, playgroundNearby,
            bbqplace, animalstosee, shop,
            accesDisability, accesStroller, indoor, false,
            duration, ageFrom, price, openinghours, destinationImage, destinationPath
        )


        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Namn och beskrivning får inte vara tomt",
                Toast.LENGTH_LONG
            ).show()


        } else {

            db.collection("destinations").add(destination)
            finish()
        }


    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()

    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    fun stopLocationUpdates() {
        locationProvider.removeLocationUpdates(locationCallback)
    }


    fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                permission.ACCESS_FINE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            locationProvider.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )

        }
    }


    private fun selectImage() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)

    }


    private fun uploadImgae() {


        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        imageFileName = fileName.toString()
        destinationPath = "images/${imageFileName}"
        Log.d("###", "${imageFileName}")

        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(imageUri).addOnSuccessListener {


            imageUploadBtn.visibility = View.GONE
            takeInPick.setImageURI(imageUri)
            Toast.makeText(this@AddDestinationsActivity, "Successfuly upladed", Toast.LENGTH_SHORT)
                .show()
            if (progressDialog.isShowing) progressDialog.dismiss()

        }.addOnFailureListener {

            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@AddDestinationsActivity, "failed", Toast.LENGTH_SHORT).show()

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {

            imageUri = data?.data!!

            takeInPick.setImageURI(imageUri)
            uploadImgae()

        }


    }


    //location
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION) {

            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                startLocationUpdates()


            }


        }
    }


}
