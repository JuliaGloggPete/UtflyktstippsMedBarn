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

        val loggedIn = intent.getBooleanExtra("logged", true)




        val addDestinationButton = findViewById<FloatingActionButton>(R.id.FABtoAdd)
        addDestinationButton.setOnClickListener {


            if (loggedIn == true) {

                val intent = Intent(this, AddDestinationsActivity::class.java)
                startActivity(intent)
            } else {
                addNRemovePleaseLogInFragment()
            }
        }
        val logOutButton = findViewById<Button>(R.id.btn_LogOut)
        if (loggedIn == false) {
            logOutButton.setText("Logga In")
        }



        logOutButton.setOnClickListener {
            if (loggedIn == false) {
                finish()
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Logout",true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent) }

        val mapButton = findViewById<FloatingActionButton>(R.id.floatingActionButtonMaps)

        mapButton.setOnClickListener {

            val intent = Intent(this, AllDestinationsMapsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()

    }

    override fun OnClick(position: Int) {
        val intent = Intent(this, MoreInfoActivity::class.java)

        intent.putExtra("id", DataManager.destinations[position].documentId.toString())
        startActivity(intent)


    }

    fun addNRemoveLegendFragment(view: View) {

        val frag = supportFragmentManager.findFragmentByTag("legendFragment")
        if (frag != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(legendFragment)
            transaction.commit()
        } else {

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framelayout, legendFragment, "legendFragment")
            transaction.commit()

        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_UP) {
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
        if (frag != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(plsLogInFragment)
            transaction.commit()
        } else {


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


