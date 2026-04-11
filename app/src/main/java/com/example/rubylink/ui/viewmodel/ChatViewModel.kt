package com.example.rubylink.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import com.example.rubylink.data.model.Message

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    fun sendMessage(text: String, chatId: String) {
        val newMessage = Message(
            id = UUID.randomUUID().toString(),
            text = text,
            sender = "Me",
            timestamp = System.currentTimeMillis(),
            chatId = chatId
        )
        _messages.value = _messages.value + newMessage
    }
}