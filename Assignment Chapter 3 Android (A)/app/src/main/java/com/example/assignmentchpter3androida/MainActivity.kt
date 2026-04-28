package com.example.assignmentchpter3androida

import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        //Name Edit Text
        val etName = findViewById<EditText>(R.id.etName)

        //Toast the String which was written by user in Field
        etName.setOnEditorActionListener { v, actionID, _ ->
            if (actionID == EditorInfo.IME_ACTION_DONE) {
                Toast.makeText(this, "Hello! ${v.text}", Toast.LENGTH_SHORT).show()
                val imm =
                    v.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
            }
        }

        //Email Edit Text
        val etEmail = findViewById<EditText>(R.id.etEmail)

        //Check weather the user written correct formate of email or not!
        etEmail.setOnEditorActionListener { v, actionID, _ ->
            if (actionID == EditorInfo.IME_ACTION_DONE) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(v.text).matches()) {
                    Toast.makeText(this, "Correct Formate", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show()
                }
                val imm =
                    v.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
            }
        }

        //Radio Button Group For Gender
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)

        //Toast the message according the selected gender
        rgGender.setOnCheckedChangeListener { _,checkID ->
            val msgText = when (checkID) {
                R.id.rbMale -> "Your Gender is male."
                R.id.rbFemale -> "Your Gender is female."
                R.id.rbOther -> "Your Gender is other."
                else -> "Please Select Valid Gender."
            }
            Toast.makeText(this, msgText, Toast.LENGTH_SHORT).show()
        }

        //Normal Toast Button
        val btnNormalToast = findViewById<Button>(R.id.btnNormalToast)

        //Toast  Normal Default Toast
        btnNormalToast.setOnClickListener {
            showNormalToast()
        }

        //Custom Toast Button
        val btnCustomToast = findViewById<Button>(R.id.btnCustomToast)

        //Toast Custom Toast
        btnCustomToast.setOnClickListener {
            showCustomToast()
        }
    }

    private  fun showNormalToast() {
        Toast.makeText(this, "Normal Toast is Shown", Toast.LENGTH_SHORT).show()
    }
    private fun showCustomToast() {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, null)
        val myToast = Toast(applicationContext)
        myToast.apply {
            setGravity(Gravity.BOTTOM,0,0)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}