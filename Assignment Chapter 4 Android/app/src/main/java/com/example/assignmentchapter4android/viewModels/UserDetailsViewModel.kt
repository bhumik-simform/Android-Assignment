package com.example.assignmentchapter4android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentchapter4android.AppModule
import com.example.assignmentchapter4android.network.NetworkResult
import kotlinx.coroutines.launch

class UserDetailsViewModel : ViewModel() {

    private val repository = AppModule.provideRepository()

    private val _uiState = MutableLiveData<UserDetailsUiState>()
    val uiState: LiveData<UserDetailsUiState>
        get() = _uiState

    fun getUser(userId: Int) {

        _uiState.value = UserDetailsUiState.Loading

        viewModelScope.launch {

            when(val result = repository.getUser(userId)) {

                is NetworkResult.Success -> {
                    _uiState.value = UserDetailsUiState.Success(result.data)
                }

                is NetworkResult.Error -> {
                    _uiState.value = UserDetailsUiState.Error(result.networkError)
                }
            }
        }
    }
}