package com.local.lift.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _userFullName = MutableLiveData<String>()
    val userFullName: LiveData<String> get() = _userFullName

    fun setUserFullName(fullName: String) {
        _userFullName.value = fullName
    }
}