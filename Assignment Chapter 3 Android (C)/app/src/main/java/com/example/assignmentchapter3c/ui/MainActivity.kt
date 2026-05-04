package com.example.assignmentchapter3c.ui


import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter3c.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val profileFragment = ProfileFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        if (savedInstanceState == null) {
            Log.d("Enter New Fragment","This is Home Fragment")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit()
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    Log.d("Enter New Fragment","This is Home Fragment")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, homeFragment)
                        .commit()
                    true
                }

                R.id.nav_search -> {
                    Log.d("Enter New Fragment","This is Search Fragment")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,searchFragment)
                        .commit()
                    true
                }

                R.id.nav_profile -> {
                    Log.d("Enter New Fragment","This is profile Fragment")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,profileFragment)
                        .commit()
                    true
                }

                else -> false
            }

        }
    }
}
