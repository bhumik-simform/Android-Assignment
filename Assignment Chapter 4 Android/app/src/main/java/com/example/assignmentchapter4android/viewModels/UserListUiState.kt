package com.example.assignmentchapter4android.viewModels

import com.example.assignmentchapter4android.model.User

sealed class UserListUiState {

    data object Loading: UserListUiState()

    data class Success(
        val data: List<User>
    ): UserListUiState()

    data class Error(
        val message: String
    ): UserListUiState()
}