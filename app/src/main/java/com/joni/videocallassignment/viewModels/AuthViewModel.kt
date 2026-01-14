/*
package com.joni.videocallassignment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joni.videocallassignment.models.User
import com.joni.videocallassignment.models.UserType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError

    // Mock users for demo (in real app, use database)
    private val mockUsers = listOf(
        User("1", "John Patient", "patient@email.com", "123456", UserType.PATIENT),
        User("2", "Dr. Smith", "doctor@email.com", "123456", UserType.DOCTOR, "Cardiologist"),
        User("3", "Sarah Wilson", "sarah@email.com", "123456", UserType.PATIENT),
        User("4", "Dr. Johnson", "johnson@email.com", "123456", UserType.DOCTOR, "Neurologist")
    )

    fun login(email: String, password: String, userType: UserType) {
        viewModelScope.launch {
            val user = mockUsers.find {
                it.email == email && it.password == password && it.userType == userType
            }

            if (user != null) {
                _currentUser.value = user
                _loginError.value = null
            } else {
                _loginError.value = "Invalid credentials or user type"
            }
        }
    }

    fun logout() {
        _currentUser.value = null
    }
}*/
