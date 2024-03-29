package com.example.norraskanefamiljeutflyktsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var emailView: EditText
    lateinit var passwordView: EditText
    lateinit var skipBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        auth = Firebase.auth


        val db = Firebase.firestore


        val docRef = db.collection("destinations")

        docRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null) {

                DataManager.destinations.clear()

                for (document in snapshot.documents) {
                    val item = document.toObject<Places>()
                    if (item != null) {
                        DataManager.destinations.add(item)

                    }
                }
            }
            printPlacesItems()
        }




        emailView = findViewById(R.id.editTextTextEmailAddress)
        passwordView = findViewById(R.id.editTextTextPassword)

        val signInBtn = findViewById<Button>(R.id.btn_signIn)
        val signUpBtn = findViewById<Button>(R.id.btn_signUp)

        skipBtn = findViewById<Button>(R.id.btn_moveOnWithoutRegister)


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
        for (item in DataManager.destinations) {

            Log.d("HHH", "${item.title}")

        }

    }


    fun gotoPlacesActivitiy() {
        var loggedIn = true


        if (skipBtn.isPressed) {
            loggedIn = false

        }


        val intent = Intent(this, DestinationsViewActivity::class.java)
        //val intent = Intent(this, TakeInImageActivity::class.java)

        intent.putExtra("logged", loggedIn)
        startActivity(intent)

    }


    fun signUp() {
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            val toast =
                Toast.makeText(
                    this, "Email eller lösenord saknas",
                    Toast.LENGTH_SHORT
                )
            toast.show()
            return

        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val toast =
                    Toast.makeText(
                        this, "Välkommen",
                        Toast.LENGTH_SHORT
                    )
                toast.show()
                gotoPlacesActivitiy()


            } else {
                val toast =
                    Toast.makeText(
                        this, "Något gick fel",
                        Toast.LENGTH_SHORT
                    )
                toast.show()

                Log.d("###", "${task.exception}")


            }

        }


    }

    override fun onResume() {
        super.onResume()
       val logout = intent.getBooleanExtra("Logout", false)

        if(logout == false){
            auth.signOut()
        }

    }

    fun signIn() {
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            val toast =
                Toast.makeText(
                    this, "Email eller lösenord saknas",
                    Toast.LENGTH_SHORT
                )
            toast.show()
            return

        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val toast =
                    Toast.makeText(
                        this, "Välkommen",
                        Toast.LENGTH_SHORT
                    )
                toast.show()

                gotoPlacesActivitiy()

            } else {
                val toast =
                    Toast.makeText(
                        this, "Något gick fel ",
                        Toast.LENGTH_SHORT
                    )
                toast.show()

                Log.d("###", "${task.exception}")

            }

        }

    }


}
