package com.example.norraskanefamiljeutflyktsapp

import com.google.firebase.firestore.DocumentId


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

    var price: String? = null,
    var openHours: String? = null,
    var destinationImage: Int? = null,
    var destinationImagePath :String = "",
    @DocumentId var documentId : String? = null,
    )
