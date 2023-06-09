package com.app.courseapp.Activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.app.courseapp.Fragment.HomeFragment
import com.app.courseapp.Fragment.MyLearningFragment
import com.app.courseapp.Fragment.ProfileFragment
import com.app.courseapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() , NavigationBarView.OnItemSelectedListener {


    var fragment_container: FrameLayout? = null
    var bottomNavigationView: BottomNavigationView? = null

    var fm: FragmentManager? = null
    var homeFragment: HomeFragment = HomeFragment()
    var profileFragment: ProfileFragment = ProfileFragment()
    var myLearningFragment: MyLearningFragment = MyLearningFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fm = supportFragmentManager

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView!!.setOnItemSelectedListener(this)

        fm!!.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment()).commit()
        bottomNavigationView!!.setSelectedItemId(R.id.navHome)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.navHome -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment).commit()
                return true
            }

            R.id.navMyLearning -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, myLearningFragment).commit()
                return true
            }

            R.id.navAccount -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, profileFragment).commit()
                return true
            }


        }
        return false
    }
}