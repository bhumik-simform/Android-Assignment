package com.example.assignmentchapter3androidb

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val nameEditText = findViewById<TextInputEditText>(R.id.edit_text_name)
        val emailEditText = findViewById<TextInputEditText>(R.id.edit_text_email)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.tv_auto_complete)
        val feedbackEditText = findViewById<TextInputEditText>(R.id.edit_text_feedback)


        //When user clicks on next button
        nameEditText.setOnEditorActionListener { _, actionId, _ ->
            //Check the action
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                val inputText = nameEditText.text.toString()
                //Check if string is empty or not
                if (inputText.isEmpty()) {
                    nameEditText.error = "Please Enter Your Name"
                } else {
                    //If input is valid than shift focus on next edit text
                    nameEditText.error = null
                    emailEditText.requestFocus()
                }
                true
            } else {
                false
            }
        }


        emailEditText.setOnEditorActionListener { v, actionId, _ ->
            //Check the action
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                val inputEmail = emailEditText.text.toString()
                    //Check the formate of email
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
                    emailEditText.error = null
                    //Shift focus on dropdown menu
                    autoCompleteTextView.requestFocus()
                    //Open dropdown menu
                    autoCompleteTextView.showDropDown()

                    //Hide keyboard
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)

                } else if (inputEmail.isEmpty()) {
                    emailEditText.error = "Please enter your email."
                } else {
                    emailEditText.error = "Please enter valid email."
                }

                true
            } else {
                false
            }
        }

        val items = arrayOf("Issue", "Suggestion", "Question", "Praise")
        val adapter = ArrayAdapter<String>(this, R.layout.item_list, items)

        autoCompleteTextView.setAdapter(adapter)

        feedbackEditText.setOnFocusChangeListener { v, hasFocus ->
            //Checks if user click outside the field
            if (!hasFocus) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }
}
