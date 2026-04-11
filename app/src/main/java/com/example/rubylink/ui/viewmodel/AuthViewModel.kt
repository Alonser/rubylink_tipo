package com.example.rubylink.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _user = MutableStateFlow(auth.currentUser)
    val user: StateFlow<Any?> = _user

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    fun logout() {
        auth.signOut()
        _user.value = null
    }

    fun getUserId(): String {
        return auth.currentUser?.uid ?: ""
    }
}