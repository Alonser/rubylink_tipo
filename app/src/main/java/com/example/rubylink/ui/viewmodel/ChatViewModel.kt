package com.example.rubylink.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.rubylink.data.Message

class ChatViewModel : ViewModel() {

    // хранение сообщений по пользователю
    private val _chats = MutableStateFlow<Map<String, List<Message>>>(emptyMap())
    val chats: StateFlow<Map<String, List<Message>>> = _chats

    fun getMessages(userName: String): List<Message> {
        return _chats.value[userName] ?: emptyList()
    }

    fun sendMessage(userName: String, text: String) {
        val message = Message(text, true)
        val updated = (_chats.value[userName] ?: emptyList()) + message
        _chats.value = _chats.value + (userName to updated)
    }

    fun receiveMessage(userName: String, text: String) {
        val message = Message(text, false)
        val updated = (_chats.value[userName] ?: emptyList()) + message
        _chats.value = _chats.value + (userName to updated)
    }
}