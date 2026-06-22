package com.example.assignmentchapter4android.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.databinding.ActivityLoginBinding
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

        setupOnclick()
    }

    private fun setupOnclick() {

        val userName = binding.editTextUserName.text.toString()

        val password = binding.editTextUserName.text.toString()


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
                if(isValidPassword()){
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
}