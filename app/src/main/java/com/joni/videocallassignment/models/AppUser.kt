package com.joni.videocallassignment.models

enum class UserRole {
    PATIENT, DOCTOR
}

data class AppUser(
    val uid: String = "",
    val email: String = "",
    val role: UserRole = UserRole.PATIENT
)
