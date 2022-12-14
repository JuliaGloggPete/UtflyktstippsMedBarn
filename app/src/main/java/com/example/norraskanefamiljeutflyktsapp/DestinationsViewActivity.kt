package com.example.norraskanefamiljeutflyktsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DestinationsViewActivity : AppCompatActivity(), DestinatinRecyclerAdapter.OnClickListener{

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinations_view)

        recyclerView=findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DestinatinRecyclerAdapter(this, DataManager.destinations,this )
        val addDestinationButton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        addDestinationButton.setOnClickListener{

            //if  sign up // sign in = true
            // skick med över från innan

            val intent = Intent (this, TestScrollDownAdd::class.java)
            startActivity(intent)

        }

        val mapBtn = findViewById<Button>(R.id.btn_seeMap)
        mapBtn.setOnClickListener {

            val intent = Intent (this, AllDestinationsMapsActivity :: class.java)
            startActivity(intent)


        }



    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()

    }

    fun addMoreFragment (view: View){
        val moreFragment = MoreInfoFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container,moreFragment,"more Fragment")
        transaction.commit()


    }

    override fun OnClick(position: Int) {
      // Toast.makeText(this, "${position}",Toast.LENGTH_SHORT).show()
     //   Toast.makeText(this,DataManager.destinations[position].title,Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MoreInfoActivity ::class.java)

        intent.putExtra("title",DataManager.destinations[position].title.toString())
        startActivity(intent)


    }

}