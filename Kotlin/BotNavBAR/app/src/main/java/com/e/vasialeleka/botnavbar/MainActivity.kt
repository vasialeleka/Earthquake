package com.e.vasialeleka.botnavbar

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.e.vasialeleka.botnavbar.fragments.FragmentOne
import com.e.vasialeleka.botnavbar.fragments.FragmentThree
import com.e.vasialeleka.botnavbar.fragments.FragmentTwo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val manager = supportFragmentManager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
createFragmentOne()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
createFragmentTwo()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
createFragmentThree()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
createFragmentOne()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun createFragmentOne() {

        val transaction = manager.beginTransaction()
        val fragment = FragmentOne()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun createFragmentTwo() {

        val transaction = manager.beginTransaction()
        val fragment = FragmentTwo()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun createFragmentThree() {

        val transaction = manager.beginTransaction()
        val fragment = FragmentThree()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
