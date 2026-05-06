package com.example.assignmentchapter3c.ui

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.adapter.OsRecyclerAdapter
import com.example.assignmentchapter3c.data.osDataList
import com.example.assignmentchapter3c.decor.RecyclerItemDividerDecoration

class RecyclerViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val osRecyclerView = findViewById<RecyclerView>(R.id.os_recycler_view)
        osRecyclerView.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        osRecyclerView.adapter = OsRecyclerAdapter(this, osDataList) { currItemView ->
            val itemCheckBox = currItemView.findViewById<CheckBox>(R.id.cb_rv)
            itemCheckBox.toggle()
        }
        osRecyclerView.addItemDecoration(RecyclerItemDividerDecoration())
    }
}