package com.example.assignmentchapter3c.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Space
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter3c.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + 40,
                systemBars.top,
                systemBars.right + 40,
                systemBars.bottom
            )
            insets
        }

        val listViewBtn = findViewById<Button>(R.id.btn_listview)
        listViewBtn.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }

        val recyclerViewBtn = findViewById<Button>(R.id.btn_recyclerview)
        recyclerViewBtn.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }

        val gridViewBtn = findViewById<Button>(R.id.btn_gridview)
        gridViewBtn.setOnClickListener {
            startActivity(Intent(this, GridViewActivity::class.java))
        }
    }
}