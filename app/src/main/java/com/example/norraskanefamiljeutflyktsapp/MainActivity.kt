package com.example.norraskanefamiljeutflyktsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
class MainActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var emailView : EditText
    lateinit var passwordView: EditText

    val placesList = mutableListOf<Places>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth


        val db = Firebase.firestore


       /* val destination1 =Places("Wanås Slott","Wanås",
            "28990 Knislinge",
            "www.wanaskonst.se","Underbar vacker trädgård med konst som man kan ta på. " +
                    "Barnen uppskattade att de fick klättra upp udda konstvärk och även vi vuxna var " +
                    "imponerad av slottgårdens unika konstvärk.",
            LatLng(56.18,14.04),true,false,
            false,true,true,true,true,false,
            false,"halvdagsutflykt","från 2år","vuxen 100 kr, student/pensionär 80kr, barn under 18 år fri (2022)",
            "Skulpturparken är öppen alla dagar, året runt, 10–17.", R.drawable.wanas
        )
        val destination2 = Places("Älgsafari","Misterhult 2032",
            "285 91 Markaryd",
            "ej angiven","Träffa älgar och bisons! " +
                    "Man kör själv eller med en guidad tåg igenom älgens och bisons arsenal och vara jättenära dem. " +
                    "Är man inne i arsenalen kan man välja att köra rundan fler omgångar. " +
                    "Café o restaurang på plats. OBS avvikande öppettider men butiken är alltid öppet.",
            LatLng(56.45957505466363,13.633536445828229),true,true,
            false,true,true,true,true,true,
            false,"1-2 timmar","från 2år","med egenbil: vuxen 150, barn 3-12: 100kr, med guidad tåg: vuxen 190, barn 3-12: 120kr",
            "25 juni - 6 november  Alla dagar 10.00 - 18.00, resten av året lördagar och söndagar (utom röda dagar)",R.drawable.algsafari
        )
        val destination3 = Places("Åka dressin","Misterhult 2032",
            "285 91 Markaryd",
            "ej angiven","Åka dressin mellan Broby och Glimminge",
            LatLng(56.25918449478585,14.075454474269769),false,false,
            true,false,false,true,true,false,
            false,"1-2 timmar","från 2år","150 kr per dressin",
            " efter bokning  044-440 48",R.drawable.aka_dressin
        )

        //db.collection("destinations").add(destination1)
        //db.collection("destinations").add(destination2)
        //db.collection("destinations").add(destination3) */

       val docRef = db.collection("destinations")
        
        docRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null) {
                placesList.clear()
                for (document in snapshot.documents) {
                    val item = document.toObject<Places>()
                    if (item != null) {
                        placesList.add(item)
                    }


                }


            }
            printPlacesItems()
        }
  



        emailView = findViewById(R.id.editTextTextEmailAddress)
        passwordView = findViewById(R.id.editTextTextPassword)

        val signInBtn =findViewById<Button>(R.id.btn_signIn)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)

        val skipBtn = findViewById<Button>(R.id.btn_moveOnWithoutRegister)

        signUpBtn.setOnClickListener {
            signUp()

        }

        signInBtn.setOnClickListener {
            signIn()

        }

        skipBtn.setOnClickListener {
            gotoPlacesActivitiy()
        }



    }

    fun printPlacesItems() {
        for (item in placesList){

            Log.d("HHH", "${item.title}")

        }

    }



    fun gotoPlacesActivitiy() {
        val intent = Intent(this, DestinationsViewActivity ::class.java)
        startActivity(intent)

    }



    fun signUp() {
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            val toast =
                Toast.makeText(
                    this,"Email eller lösenord saknas",
                    Toast.LENGTH_SHORT
                )
            toast.show()
            return

        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                val toast =
                    Toast.makeText(
                        this,"Välkommen",
                        Toast.LENGTH_SHORT
                    )
                toast.show()
                gotoPlacesActivitiy()

            } else{
                val toast =
                    Toast.makeText(
                        this,"Något gick fel",
                        Toast.LENGTH_SHORT
                    )
                toast.show()

                Log.d("###","${task.exception}")


            }

        }



    }
    fun signIn() {
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            val toast =
                Toast.makeText(
                    this,"Email eller lösenord saknas",
                    Toast.LENGTH_SHORT
                )
            toast.show()
            return

        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
            if(task.isSuccessful){
                val toast =
                    Toast.makeText(
                        this,"Välkommen",
                        Toast.LENGTH_SHORT
                    )
                toast.show()
                gotoPlacesActivitiy()

            } else{
                val toast =
                    Toast.makeText(
                        this,"Något gick fel ",
                        Toast.LENGTH_SHORT
                    )
                toast.show()

                Log.d("###","${task.exception}")

            }

        }



    }

}
