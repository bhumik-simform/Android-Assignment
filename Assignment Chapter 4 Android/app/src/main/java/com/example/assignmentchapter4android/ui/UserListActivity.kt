package com.example.assignmentchapter4android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.databinding.ActivityUserListBinding
import com.example.assignmentchapter4android.viewModels.UserListUiState
import com.example.assignmentchapter4android.viewModels.UserListViewModel

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding

    private val viewModel: UserListViewModel by viewModels()

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

        setupObserver()
        setupRecyclerView()
        viewModel.fetchUsers()
        setupOnClicks()
    }

    fun setupObserver() {
        viewModel.uiState.observe(this) {
            when (it) {
                is UserListUiState.Loading -> {
                    showLoadingState(true)
                }

                is UserListUiState.Success -> {
                    showLoadingState(false)
                    adapter.submitList(it.data)
                }

                is UserListUiState.Error -> {
                    showLoadingState(false)
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = UserListAdapter()
        binding.rvUserList.adapter = adapter
        binding.rvUserList.layoutManager = LinearLayoutManager(this)

    }

    private fun setupOnClicks() {
        binding.fabAddUserList.setOnClickListener {
            showAddUserDialog()
        }
    }

    private fun showLoadingState(isLoading: Boolean) {
        binding.fabAddUserList.isEnabled = !isLoading

        binding.rvUserList.visibility =
            if (isLoading) View.GONE
            else View.VISIBLE

        binding.progressCircular.visibility =
            if (isLoading) View.VISIBLE
            else View.GONE
    }

    private fun showAddUserDialog() {

    }
}