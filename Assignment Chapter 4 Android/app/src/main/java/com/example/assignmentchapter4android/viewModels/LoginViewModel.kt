package com.example.assignmentchapter4android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentchapter4android.AppModule
import com.example.assignmentchapter4android.model.LoginRequest
import com.example.assignmentchapter4android.network.NetworkResult
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = AppModule.provideRepository()

    private val _uiState = MutableLiveData<LoginUiState>()
    val uiState: LiveData<LoginUiState>
        get() = _uiState

    fun loginRequest(userName: String, password: String) {

        val request = LoginRequest(userName, password)
        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            when (val result = repository.login(request)) {
                is NetworkResult.Success -> {
                    _uiState.value = LoginUiState.Success(result.data)
                }

                is NetworkResult.Error -> {
                    _uiState.value = LoginUiState.Error(result.networkError)
                }
            }
        }
    }
}