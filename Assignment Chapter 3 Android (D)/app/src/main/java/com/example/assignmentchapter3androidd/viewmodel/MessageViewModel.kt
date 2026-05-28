package com.example.assignmentchapter3androidd.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageViewModel: ViewModel() {

    private val _message = MutableLiveData<String>()

    val message: LiveData<String>
        get() = _message

    fun saveMessage(newMessage: String) {
        _message.value = newMessage
    }
}