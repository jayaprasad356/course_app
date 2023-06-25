package com.app.courseapp.Activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.courseapp.Fragment.HomeFragment
import com.app.courseapp.Fragment.MyLearningFragment
import com.app.courseapp.Fragment.ProfileFragment
import com.app.courseapp.R
import com.app.courseapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private var fragment_container: FrameLayout? = null
    private var bottomNavigationView: BottomNavigationView? = null
    lateinit var binding: ActivityMainBinding

    private lateinit var fm: FragmentManager
    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var myLearningFragment: MyLearningFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        fm = supportFragmentManager
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        myLearningFragment = MyLearningFragment()

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView!!.setOnItemSelectedListener(this)

        fm.beginTransaction()
            .replace(R.id.fragment_container, homeFragment)
            .commit()
        bottomNavigationView!!.selectedItemId = R.id.navHome




    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val transaction: FragmentTransaction = fm.beginTransaction()

        when (item.itemId) {
            R.id.navHome -> {


//                transaction.setCustomAnimations(
//                    R.anim.slide_in_top_right,
//                    R.anim.slide_out_bottom_left
//                )
                transaction.replace(R.id.fragment_container, homeFragment)
            }

            R.id.navMyLearning -> {
//                transaction.setCustomAnimations(
//                    R.anim.slide_in_top_right,
//                    R.anim.slide_out_bottom_left
//                )
                transaction.replace(R.id.fragment_container, myLearningFragment)
            }

            R.id.navAccount -> {

                transaction.setCustomAnimations(
                    R.anim.slide_in_top_right,
                    R.anim.slide_out_bottom_left
                )
                transaction.replace(R.id.fragment_container, profileFragment)
            }
        }



        transaction.commit()
        return true
    }
}
