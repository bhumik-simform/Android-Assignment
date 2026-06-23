package com.example.assignmentchapter4android.viewModels

import com.example.assignmentchapter4android.model.User

sealed class UserDetailsUiState {

    object Loading : UserDetailsUiState()

    data class Success(
        val data: User
    ) : UserDetailsUiState()

    data class Error(
        val message: String
    ) : UserDetailsUiState()
}