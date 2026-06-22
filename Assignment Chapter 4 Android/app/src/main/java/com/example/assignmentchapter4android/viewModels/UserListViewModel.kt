package com.example.assignmentchapter4android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentchapter4android.AppModule
import com.example.assignmentchapter4android.network.NetworkResult
import kotlinx.coroutines.launch

class UserListViewModel: ViewModel() {

    private val repository = AppModule.provideRepository()

    private val _uiState = MutableLiveData<UserListUiState>()
    val uiState: LiveData<UserListUiState>
        get() = _uiState


    fun fetchUsers() {
        _uiState.value = UserListUiState.Loading

        viewModelScope.launch {
            when (val result = repository.getUsers()) {
                is NetworkResult.Success -> {
                    _uiState.value = UserListUiState.Success(result.data.users)
                }

                is NetworkResult.Error -> {
                    _uiState.value = UserListUiState.Error(result.networkError)
                }
            }
        }
    }

}