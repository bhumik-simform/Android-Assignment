package com.example.assignmentchapter4android.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.databinding.ActivityAddUserBinding
import com.example.assignmentchapter4android.model.CreateUserRequest

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupOnClicks()
    }

    private fun setupOnClicks() {

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnSubmit.setOnClickListener {
            if(isValidData()) {
                val intent = Intent()
                val newUser = getRequest()
                if (newUser != null) {
                    intent.putExtra("NEW_CREATED_USER",newUser)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }

    }
    fun isValidData(): Boolean {

        val firstName = binding.editTextFirstName.text.toString()
        val lastName = binding.editTextLastName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val userName = binding.editTextUserName.text.toString()
        val imageUrl = binding.editTextImageUrl.text.toString()

        when {

            firstName.isBlank() -> {
                binding.editTextFirstName.apply {
                    error = "First name required"
                    requestFocus()
                }
                return false
            }

            lastName.isBlank() -> {
                binding.editTextLastName.apply {
                    error = "Last name required"
                    requestFocus()
                }
                return false
            }

            email.isBlank() -> {
                binding.editTextEmail.apply {
                    error = "Email required"
                    requestFocus()
                }
                return false
            }

            isValidEmail(email) -> {
                binding.editTextEmail.apply {
                    error = "Add valid email"
                    requestFocus()
                }
                return false
            }

            userName.isBlank() -> {
                binding.editTextUserName.apply {
                    error = "User name required"
                    requestFocus()
                }
                return false
            }

            imageUrl.isBlank() -> {
                Log.d("NewUser"," Blank ImageURL")
                binding.editTextImageUrl.apply {
                    error = "Image Link Required"
                    requestFocus()
                }
                return false
            }

            isValidImageUrl(imageUrl) -> {
                binding.editTextImageUrl.apply {
                    error = "Add valid email"
                    requestFocus()
                }
                return false
            }
        }

        binding.apply {
            editTextFirstName.error = null
            editTextLastName.error = null
            editTextEmail.error = null
            editTextUserName.error = null
            editTextImageUrl.error = null
        }

        return true
    }

    private fun getRequest(): CreateUserRequest? {
        if(!isValidData()) return null

        return CreateUserRequest(
            firstName = binding.editTextFirstName.text.toString(),
            lastName = binding.editTextLastName.text.toString(),
            email = binding.editTextEmail.text.toString(),
            userName = binding.editTextUserName.text.toString(),
            imageUrl = binding.editTextImageUrl.text.toString()
        )

    }

    private fun isValidEmail(email: String) = !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidImageUrl(url: String) = !Patterns.WEB_URL.matcher(url).matches()
}