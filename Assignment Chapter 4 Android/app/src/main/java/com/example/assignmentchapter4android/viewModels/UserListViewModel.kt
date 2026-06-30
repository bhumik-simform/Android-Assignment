package com.example.assignmentchapter4android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentchapter4android.AppModule
import com.example.assignmentchapter4android.model.CreateUserRequest
import com.example.assignmentchapter4android.model.User
import com.example.assignmentchapter4android.network.NetworkResult
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val repository = AppModule.provideRepository()

    private val _uiState = MutableLiveData<UserListUiState>()
    val uiState: LiveData<UserListUiState>
        get() = _uiState

    private var usersList = listOf<User>()

    fun fetchUsers() {
        _uiState.value = UserListUiState.Loading

        viewModelScope.launch {
            when (val result = repository.getUsers()) {
                is NetworkResult.Success -> {
                    usersList = result.data.users
                    _uiState.value = UserListUiState.Success(usersList)
                }

                is NetworkResult.Error -> {
                    _uiState.value = UserListUiState.Error(result.networkError)
                }
            }
        }
    }

    fun createUser(newUser: CreateUserRequest) {
        _uiState.value = UserListUiState.Loading
        viewModelScope.launch {
            when (val result = repository.createUser(newUser)) {
                is NetworkResult.Success -> {
                    usersList = listOf(result.data) + usersList
                    _uiState.value = UserListUiState.Success(usersList)
                }

                is NetworkResult.Error -> {
                    _uiState.value = UserListUiState.Error(result.networkError)
                }
            }
        }
    }

}