package com.example.norraskanefamiljeutflyktsapp
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


object DataManager {

    val destinations = mutableListOf<Places>()


    init {
        createBaseData()

    }


    fun createBaseData(){

        destinations.add(Places("Wanås Slott","Wanås",
        "28990 Knislinge",
            "www.wanaskonst.se","Underbar vacker trädgård med konst som man kan ta på. " +
                    "Barnen uppskattade att de fick klättra upp udda konstvärk och även vi vuxna var " +
                    "imponerad av slottgårdens unika konstvärk.",
           56.18,14.04,
            true,false,
            false,true,true,true,true,false,
            false,"halvdagsutflykt","från 2år","vuxen 100 kr, student/pensionär 80kr, barn under 18 år fri (2022)",
            "Skulpturparken är öppen alla dagar, året runt, 10–17.", R.drawable.wanas
        ))
        destinations.add(Places("Älgsafari","Misterhult 2032",
        "285 91 Markaryd",
            "ej angiven","Träffa älgar och bisons! " +
                    "Man kör själv eller med en guidad tåg igenom älgens och bisons arsenal och vara jättenära dem. " +
                    "Är man inne i arsenalen kan man välja att köra rundan fler omgångar. " +
                    "Café o restaurang på plats. OBS avvikande öppettider men butiken är alltid öppet.",
            56.45957505466363,13.633536445828229,
            true,true,
            false,true,true,true,true,true,
            false,"1-2 timmar","från 2år","med egenbil: vuxen 150, barn 3-12: 100kr, med guidad tåg: vuxen 190, barn 3-12: 120kr",
            "25 juni - 6 november  Alla dagar 10.00 - 18.00, resten av året lördagar och söndagar (utom röda dagar)",R.drawable.algsafari
        ))
          destinations.add(Places("Åka dressin","Misterhult 2032",
        "285 91 Markaryd",
            "ej angiven","Åka dressin mellan Broby och Glimminge",
            56.25918449478585,14.075454474269769,
              false,false,
            true,false,false,true,true,false,
            false,"1-2 timmar","från 2år","150 kr per dressin",
            " efter bokning  044-440 48",R.drawable.aka_dressin
        ))






    }


}