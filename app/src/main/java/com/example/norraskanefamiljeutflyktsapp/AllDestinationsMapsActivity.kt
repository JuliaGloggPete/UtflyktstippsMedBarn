package com.example.norraskanefamiljeutflyktsapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.norraskanefamiljeutflyktsapp.DataManager.destinations
import com.example.norraskanefamiljeutflyktsapp.databinding.ActivityAllDestinationsMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class AllDestinationsMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityAllDestinationsMapsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllDestinationsMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnBack2DestinationView = findViewById<Button>(R.id.btn_back_from_maps)
        btnBack2DestinationView.setOnClickListener {
            finish()
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val adapter = PlacesInfoAdapter(this)
        mMap.setInfoWindowAdapter(adapter)
        createPlaces()


        // Add a marker in Sydney and move the camera
        /*  val sydney = LatLng(-34.0, 151.0)
          mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
          mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }


    fun createPlaces() {

        val boundBuilder = LatLngBounds.builder()
        for (destination in destinations) {


            if (destination.latitude != null && destination.longitude != null) {


                var latitude = destination.latitude
                var longitude = destination.longitude

                var destinatinon1 = LatLng(latitude!!, longitude!!)
                boundBuilder.include(destinatinon1)
                val marker = mMap.addMarker(
                    MarkerOptions().position(destinatinon1)
                )
                marker?.tag = destination


            }


        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundBuilder.build(), 1000, 1000, 0))


    }

}


