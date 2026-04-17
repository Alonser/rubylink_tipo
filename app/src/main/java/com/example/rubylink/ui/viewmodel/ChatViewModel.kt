package com.example.rubylink.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rubylink.data.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    // Хранилище сообщений
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    // Функция для отправки сообщения
    fun sendMessage(text: String, chatId: String, isFromMe: Boolean) {
        viewModelScope.launch {
            val newMessage = Message(
                id = System.currentTimeMillis().toString(),
                text = text,
                isFromMe = isFromMe,
                timestamp = System.currentTimeMillis(),
                chatId = chatId
            )
            _messages.value = _messages.value + newMessage
        }
    }

    // Функция для получения сообщений чата
    fun getMessagesForChat(chatId: String): List<Message> {
        return _messages.value.filter { it.chatId == chatId }
    }

    // Функция для добавления тестового сообщения
    fun addTestMessage(chatId: String) {
        viewModelScope.launch {
            val testMessages = listOf(
                "Привет!", "Как дела?", "Что нового?",
                "Скинь фото", "Понял", "Согласен",
                "Нет", "Да", "Может быть", "Отлично!"
            )
            val randomMessage = testMessages.random()
            val newMessage = Message(
                id = System.currentTimeMillis().toString(),
                text = randomMessage,
                isFromMe = false,
                timestamp = System.currentTimeMillis(),
                chatId = chatId
            )
            _messages.value = _messages.value + newMessage
        }
    }

    // Очистка сообщений (опционально)
    fun clearMessages() {
        _messages.value = emptyList()
    }
}