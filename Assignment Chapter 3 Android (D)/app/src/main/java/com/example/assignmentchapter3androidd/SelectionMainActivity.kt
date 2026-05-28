package com.example.assignmentchapter3androidd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter3androidd.main.FragManActivity
import com.example.assignmentchapter3androidd.main.JetNavActivity

class SelectionMainActivity : AppCompatActivity() {

    private lateinit var fragManBtn: Button
    private lateinit var jetNavBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selection_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fragManBtn = findViewById(R.id.btn_frag_man)
        jetNavBtn = findViewById(R.id.btn_jet_nav)

        setOnClick()
    }

    private fun setOnClick() {

        fragManBtn.setOnClickListener {
            val intent = Intent(this, FragManActivity :: class.java)
            startActivity(intent)
        }

        jetNavBtn.setOnClickListener {
            val intent = Intent(this, JetNavActivity :: class.java)
            startActivity(intent)
        }
    }
}