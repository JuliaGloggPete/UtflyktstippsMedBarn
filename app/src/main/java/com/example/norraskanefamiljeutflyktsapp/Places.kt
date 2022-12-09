package com.example.norraskanefamiljeutflyktsapp

import com.google.android.gms.maps.model.LatLng
import java.time.Duration


data class Places(
    var title : String? = null,
    var description : String?= null,
var latitude : Float? = null,
    var longitude : Float? = null,
    var playgroundNearby : Boolean = false,
var indoorActivity : Boolean = false,
    var duration: String? = null,
var ageFrom : String? = null,
    var restaurant : String? = null,
    var pris: String? = null,
var öppetider: String? = null,
    var adress: String? = null)
