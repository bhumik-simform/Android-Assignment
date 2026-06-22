package com.example.assignmentchapter4android.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupOnClicks()
    }

    private fun setupRecyclerView() {

        binding.rvUserList.adapter = adapter


    }

    private fun setupOnClicks() {
        binding.fabAddUserList.setOnClickListener {
            showAddUserDialog()
        }
    }

    private fun showAddUserDialog() {

    }
}