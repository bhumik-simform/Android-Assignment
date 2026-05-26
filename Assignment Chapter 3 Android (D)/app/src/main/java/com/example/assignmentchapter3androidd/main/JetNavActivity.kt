package com.example.assignmentchapter3androidd.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.assignmentchapter3androidd.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class JetNavActivity : AppCompatActivity() {

    private lateinit var jetNavBottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jet_nav)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        jetNavBottomNav = findViewById(R.id.jet_nav_bottom_nav)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.jet_nav_host_fragment) as androidx.navigation.fragment.NavHostFragment
        val navController = navHostFragment.navController

        jetNavBottomNav.setupWithNavController(navController)

    }
}