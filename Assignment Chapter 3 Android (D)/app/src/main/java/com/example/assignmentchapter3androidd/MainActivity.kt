package com.example.assignmentchapter3androidd

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.assignmentchapter3androidd.fragments.AddMessageFragment
import com.example.assignmentchapter3androidd.fragments.GalleryFragment
import com.example.assignmentchapter3androidd.fragments.ViewMessageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private fun OnBackPressedDispatcher.addCallback(
    owner: MainActivity,
    onBackPressedCallback: Any
) {
}

class MainActivity : AppCompatActivity() {

    private lateinit var mainBottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        if(savedInstanceState == null){
            inflateFragment(GalleryFragment())
        }
        setupBottomNav()
    }

    private fun setupBottomNav() {
        mainBottomNav = findViewById(R.id.main_bottom_nav)

        mainBottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_gallery -> inflateFragment(GalleryFragment())
                R.id.nav_add_message -> inflateFragment(AddMessageFragment())
                R.id.nav_view_message -> inflateFragment(ViewMessageFragment())
            }

            true
        }
    }

    private fun inflateFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_container, fragment)
        }.commit()
    }
}