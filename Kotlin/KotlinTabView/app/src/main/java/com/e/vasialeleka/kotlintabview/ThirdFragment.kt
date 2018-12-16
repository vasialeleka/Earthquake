package com.e.vasialeleka.kotlintabview


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // var first = EarthQuake(1.1,"rsf")


        return inflater.inflate(R.layout.fragment_third, container, false)
    }


}
