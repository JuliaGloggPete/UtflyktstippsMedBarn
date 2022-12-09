package com.example.norraskanefamiljeutflyktsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
class MainActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var emailView : EditText
    lateinit var passwordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

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

    fun gotoPlacesActivitiy() {
        val intent = Intent(this, AllDestinationsMapsActivity ::class.java)
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
