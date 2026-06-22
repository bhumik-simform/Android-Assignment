package com.example.assignmentchapter4android

import com.example.assignmentchapter4android.network.NetworkManager
import com.example.assignmentchapter4android.network.RetrofitGsonNetworkManager
import com.example.assignmentchapter4android.network.RetrofitManualNetworkManager
import com.example.assignmentchapter4android.network.UrlConnectionNetworkManager

object AppModule {
    private lateinit var networkType: NetworkType


    enum class NetworkType {
        URL_CONNECTION,
        RETROFIT_MANUAL,
        RETROFIT_GSON;
    }

    fun setNetworkType(networkType: NetworkType) {
        AppModule.networkType = networkType
    }

    fun provideNetworkManager(): NetworkManager {
        return when (networkType) {
            NetworkType.URL_CONNECTION -> UrlConnectionNetworkManager()
            NetworkType.RETROFIT_MANUAL -> RetrofitManualNetworkManager()
            NetworkType.RETROFIT_GSON -> RetrofitGsonNetworkManager()
        }
    }

    fun provideRepository() {

    }
}