package com.example.assignmentchapter4android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.databinding.ActivityLoginBinding
import com.example.assignmentchapter4android.viewModels.LoginUiState
import com.example.assignmentchapter4android.viewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupObserver()
        setupOnclick()
    }

    private fun setupObserver() {
        viewModel.uiState.observe(this) {
            when (it) {
                is LoginUiState.Success -> {
                    showLoadingState(false)
                    val intent = Intent(this, UserListActivity::class.java)
                    startActivity(intent)
                }

                is LoginUiState.Error -> {
                    showLoadingState(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is LoginUiState.Loading -> {
                    showLoadingState(true)
                }
            }
        }
    }

    private fun setupOnclick() {

        val userName: String by lazy {
            binding.editTextUserName.text.toString()
        }

        val password: String by lazy {
            binding.editTextPassword.text.toString()
        }


        binding.editTextUserName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (isValidUsername()) {
                    binding.editTextPassword.requestFocus()
                }
                true
            } else {
                false
            }
        }

        binding.editTextPassword.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isValidPassword()) {
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
                true
            } else {
                false
            }
        }

        binding.btnLogin.setOnClickListener {
            if (isValidInput()) {
                viewModel.loginRequest(userName, password)
            }
        }
    }


    private fun isValidInput(): Boolean {

        val userName = binding.editTextUserName.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (userName.isBlank()) {
            binding.editTextUserName.error = "Please enter user name"
            binding.editTextUserName.requestFocus()
            return false
        }

        if (password.isBlank()) {
            binding.editTextPassword.error = "Please enter password"
            binding.editTextPassword.requestFocus()
            return false
        }

        binding.editTextPassword.error = null
        binding.editTextUserName.error = null

        return true
    }

    private fun isValidUsername(): Boolean {
        val userName = binding.editTextUserName.text.toString()

        if (userName.isBlank()) {
            binding.editTextUserName.error = "Please enter user name"
            return false
        }

        binding.editTextUserName.error = null
        return true
    }

    private fun isValidPassword(): Boolean {
        val password = binding.editTextPassword.text.toString()

        if (password.isBlank()) {
            binding.editTextPassword.error = "Please enter password"
            return false
        }

        binding.editTextPassword.error = null
        return true
    }

    private fun showLoadingState(isLoading: Boolean) {
        binding.apply {
            btnLogin.isEnabled = !isLoading
            editTextUserName.isEnabled = !isLoading
            editTextPassword.isEnabled = !isLoading

            progressCircular.visibility =
                if (isLoading) View.VISIBLE
                else View.GONE
        }
    }
}