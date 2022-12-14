package com.example.norraskanefamiljeutflyktsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MoreInfoFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_more_info, container, false)


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var description = getView()?.findViewById<TextView>(R.id.tv_description_moreInfo)
        description?.setText("Hallo")

    }


}