package com.example.norraskanefamiljeutflyktsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TestScrollDownAdd : AppCompatActivity() {

    lateinit var db : FirebaseFirestore
    lateinit var title : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_scroll_down_add)

        db = Firebase.firestore

        title = findViewById(R.id.et_title)








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
}