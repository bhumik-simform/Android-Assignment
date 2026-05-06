package com.example.assignmentchapter3c.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.adapter.OsGridAdapter
import com.example.assignmentchapter3c.data.osDataList
import com.example.assignmentchapter3c.decor.GridItemSpacingDecor

class GridViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupGrid()
    }

    private fun setupGrid() {
        val osGridView = findViewById<RecyclerView>(R.id.grid_view)
        osGridView.layoutManager = GridLayoutManager(this, 2)
        osGridView.adapter = OsGridAdapter(this, osDataList)
        osGridView.addItemDecoration(GridItemSpacingDecor(2,32))
    }
}