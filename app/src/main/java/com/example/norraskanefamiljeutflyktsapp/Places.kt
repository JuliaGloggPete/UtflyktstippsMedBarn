package com.example.norraskanefamiljeutflyktsapp

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.GeoPoint


data class Places(

    var title : String? = null,
    var adressStreetName: String? = null,
    var PostalCodeNVillage: String? = null,
    var homepage: String? = null,

    var description : String? =null,
    var latitude :Double? =null,
    var longitude : Double? = null,

    var restaurant : Boolean = false,
    var playgroundNearby : Boolean = false,
    var bbqplace :Boolean = false,
    var animalstosee: Boolean =false,
    var shop: Boolean = false,
    var accesDisability: Boolean = false,
    var accesStroller : Boolean = false,
    var indoorActivity : Boolean = false,
    var extraPlaceholder : Boolean =false,

    var duration: String? = null,
    var ageFrom : String? = null,

    var pris: String? = null, //!!!!!! svenska
    var öppetider: String? = null,// svenska
    var destinationImage: Int? = null,
    @DocumentId var documentId : String? = null,
    )
