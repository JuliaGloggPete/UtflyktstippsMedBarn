package com.example.norraskanefamiljeutflyktsapp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DestinationsViewActivity : AppCompatActivity(), DestinatinRecyclerAdapter.OnClickListener {

    lateinit var recyclerView: RecyclerView
    val legendFragment = LegendFragment()
    val plsLogInFragment = Log_In_please()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinations_view)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DestinatinRecyclerAdapter(this, DataManager.destinations, this)




        val addDestinationButton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        addDestinationButton.setOnClickListener {
            val loggedIn = intent.getBooleanExtra("logged", true)

            if (loggedIn == true) {

                val intent = Intent(this, TestScrollDownAdd::class.java)
                startActivity(intent)
            } else {
               // Toast.makeText(this, "log in", Toast.LENGTH_SHORT).show()
                addNRemovePleaseLogInFragment()
            }
        }
        val mapBtn = findViewById<Button>(R.id.btn_seeMap)
        mapBtn.setOnClickListener {

            val intent = Intent(this, AllDestinationsMapsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()

    }

    override fun OnClick(position: Int) {
        // Toast.makeText(this, "${position}",Toast.LENGTH_SHORT).show()
        //   Toast.makeText(this,DataManager.destinations[position].title,Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MoreInfoActivity::class.java)

        intent.putExtra("id", DataManager.destinations[position].documentId.toString())
        startActivity(intent)


    }

    fun addNRemoveLegendFragment(view: View) {

        val frag = supportFragmentManager.findFragmentByTag("legendFragment")
        if (frag != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(legendFragment)
            transaction.commit()}


       else {

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framelayout, legendFragment, "legendFragment")
            transaction.commit()

        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event?.action == MotionEvent.ACTION_UP){
            removeLegend()
            removeLoginFragment()
        }

        return true

    }

    fun removeLegend() {
        val myLegendFragment = supportFragmentManager.findFragmentByTag("legendFragment")

        if (myLegendFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(myLegendFragment)
            transaction.commit()

        }


    }

    fun addNRemovePleaseLogInFragment() {

        val frag = supportFragmentManager.findFragmentByTag("logInFragment")
        if (frag != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(plsLogInFragment)
            transaction.commit()}


        else {


            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.LogInFrameLayout2, plsLogInFragment, "logInFragment")
            transaction.commit()

        }
    }








    fun removeLoginFragment() {

        val addPleaseLoginFragment = supportFragmentManager.findFragmentByTag("logInFragment")
        if (addPleaseLoginFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(addPleaseLoginFragment)
            transaction.commit()

        }
    }

}


