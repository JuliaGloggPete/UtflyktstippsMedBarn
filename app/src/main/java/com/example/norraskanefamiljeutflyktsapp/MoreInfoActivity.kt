package com.example.norraskanefamiljeutflyktsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ViewAnimator
import androidx.recyclerview.widget.RecyclerView

class MoreInfoActivity : AppCompatActivity() {


lateinit var titleView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        val title = intent.getStringExtra("title")

        titleView = findViewById<TextView>(R.id.tv_moreInfo_title)

        titleView.text =title










    }
}