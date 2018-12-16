package com.e.vasialeleka.navbar.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.e.vasialeleka.navbar.R
import com.e.vasialeleka.navbar.adapter.MyFragmentAdapter
import kotlinx.android.synthetic.main.fragment_fragment_one.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentOne : Fragment() {
//val manager :FragmentManager = childFragmentManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v:View = inflater.inflate(R.layout.fragment_fragment_one, container, false)

       val fragmentAdapter = MyFragmentAdapter(childFragmentManager)
        v.viewPager.adapter = fragmentAdapter
        v.tabLayout.setupWithViewPager(v.viewPager)
        return v
    }


}
