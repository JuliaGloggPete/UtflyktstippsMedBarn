package com.example.norraskanefamiljeutflyktsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DestinationsViewActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinations_view)

        recyclerView=findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DestinatinRecyclerAdapter(this, DataManager.destinations )
        val addDestinationButton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        addDestinationButton.setOnClickListener{
            val intent = Intent (this, TestScrollDownAdd::class.java)
            startActivity(intent)

        }



    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()

    }

}