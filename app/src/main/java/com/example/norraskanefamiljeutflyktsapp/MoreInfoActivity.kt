package com.example.norraskanefamiljeutflyktsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ViewAnimator
import androidx.recyclerview.widget.RecyclerView

class MoreInfoActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        var adapter = DestinatinRecyclerAdapter(this, DataManager.destinations)






    }
}