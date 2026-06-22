package com.example.assignmentchapter4android.viewModels

import com.example.assignmentchapter4android.model.LoginResponse

sealed class LoginUiState {

    data object Loading: LoginUiState()

    data class Success(val response: LoginResponse): LoginUiState()

    data class Error(val message: String): LoginUiState()

}