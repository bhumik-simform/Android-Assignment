package com.example.assignmentchapter4android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.databinding.ActivityUserDetailBinding
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.viewModels.UserDetailsUiState
import com.example.assignmentchapter4android.viewModels.UserDetailsViewModel
import com.squareup.picasso.Picasso

class UserDetailActivity : AppCompatActivity() {

    private val viewModel: UserDetailsViewModel by viewModels()

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userId = intent.getIntExtra("USER_ID", -1)

        if (userId != -1) {
            fetchUser(userId)
        }
        setupObserver()
    }

    private fun fetchUser(userId: Int) {
        viewModel.getUser(userId)
    }

    private fun setupObserver() {
        viewModel.uiState.observe(this) {
            when (it) {
                is UserDetailsUiState.Error -> {
                    showLoadingState(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    finish()
                }

                is UserDetailsUiState.Loading -> {
                    showLoadingState(true)
                }

                is UserDetailsUiState.Success -> {
                    showLoadingState(false)
                    bindData(it.data)
                }
            }
        }
    }
    private fun showLoadingState(isLoading: Boolean) {
        binding.layoutContainer.visibility =
            if (isLoading) View.GONE
            else View.VISIBLE

        binding.progressCircular.visibility =
            if (isLoading) View.VISIBLE
            else View.GONE
    }
    private fun bindData(user: User) {
        binding.apply {

            Picasso.get().load(user.imageUrl).into(ivUserImage)

            tvFullName.apply {
                this.text = this.context.getString(
                    R.string.item_user_full_name,
                    user.firstName,
                    user.lastName
                )
            }

            tvUserIdValue.text = user.id.toString()
            tvUserNameValue.text = user.userName
            tvEmailValue.text = user.email
        }
    }
}