package com.example.assignmentchapter4android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.databinding.ActivityUserListBinding
import com.example.assignmentchapter4android.model.CreateUserRequest
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.viewModels.UserListUiState
import com.example.assignmentchapter4android.viewModels.UserListViewModel
class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private lateinit var adapter: UserListAdapter
    private val viewModel: UserListViewModel by viewModels()

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if(result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val newUser = data?.getParcelableExtra("NEW_CREATED_USER", CreateUserRequest::class.java)

            if(newUser!=null) {
                viewModel.createUser(newUser)
            }
        }

    }

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

        val onItemClick: (User)-> Unit = { user ->
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra("USER_ID",user.id)
            startActivity(intent)
        }

        adapter = UserListAdapter(onItemClick)
        binding.rvUserList.adapter = adapter
        binding.rvUserList.addItemDecoration(UserListDecor(32))
        binding.rvUserList.layoutManager = LinearLayoutManager(this)

    }

    private fun setupOnClicks() {
        binding.fabAddUserList.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startForResult.launch(intent)
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

}