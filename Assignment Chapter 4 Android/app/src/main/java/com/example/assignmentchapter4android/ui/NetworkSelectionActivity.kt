package com.example.assignmentchapter4android.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignmentchapter4android.AppModule
import com.example.assignmentchapter4android.R
import com.example.assignmentchapter4android.databinding.ActivityNetworkSelectionBinding

class NetworkSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNetworkSelectionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNetworkSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupOnclick()
    }

    private fun setupOnclick() {

        binding.btnUrlConnection.setOnClickListener {
            AppModule.setNetworkType(AppModule.NetworkType.URL_CONNECTION)
            navToLoginActivity()
        }

        binding.btnRetrofitManual.setOnClickListener {
            AppModule.setNetworkType(AppModule.NetworkType.RETROFIT_MANUAL)
            navToLoginActivity()
        }

        binding.btnRetrofitGson.setOnClickListener {
            AppModule.setNetworkType(AppModule.NetworkType.RETROFIT_GSON)
            navToLoginActivity()
        }

    }

    private fun navToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}