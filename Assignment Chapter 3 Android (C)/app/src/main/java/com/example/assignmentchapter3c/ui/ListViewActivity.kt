package com.example.assignmentchapter3c.ui

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.adapter.OsListAdapter
import com.example.assignmentchapter3c.data.osDataList

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupList()
    }

    private fun setupList() {
        val osListView = findViewById<ListView>(R.id.id_list_view)
        osListView.adapter = OsListAdapter(this, osDataList)
    }
}