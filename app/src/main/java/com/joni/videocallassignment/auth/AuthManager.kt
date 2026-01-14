package com.joni.videocallassignment.auth

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManager {

    private val auth = FirebaseAuth.getInstance()

    fun isLoggedIn(): Boolean = auth.currentUser != null

    fun logout() {
        auth.signOut()
    }
}
