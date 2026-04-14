package com.example.rubylink.ui.screen

import androidx.compose.runtime.mutableStateListOf

// Классы данных
data class Message(
    val id: String,
    val text: String,
    val isFromMe: Boolean,
    val timestamp: Long,
    val chatId: String
)

data class ChatInfo(
    val id: String,
    val name: String,
    var lastMessage: String,
    var lastMessageTime: Long,
    var unreadCount: Int
)

// Глобальное хранилище данных
object ChatData {
    // Список чатов
    val chats = mutableStateListOf(
        ChatInfo("1", "Анна Иванова", "Нет сообщений", System.currentTimeMillis(), 0),
        ChatInfo("2", "Дмитрий Петров", "Нет сообщений", System.currentTimeMillis(), 0),
        ChatInfo("3", "Елена Смирнова", "Нет сообщений", System.currentTimeMillis(), 0),
        ChatInfo("4", "Максим Козлов", "Нет сообщений", System.currentTimeMillis(), 0)
    )

    // Список всех сообщений
    val messages = mutableStateListOf<Message>()

    // Функция для добавления сообщения
    fun addMessage(chatId: String, text: String, isFromMe: Boolean) {
        // Добавляем сообщение
        messages.add(
            Message(
                id = System.currentTimeMillis().toString(),
                text = text,
                isFromMe = isFromMe,
                timestamp = System.currentTimeMillis(),
                chatId = chatId
            )
        )

        // Обновляем информацию о чате
        val chat = chats.find { it.id == chatId }
        chat?.let {
            it.lastMessage = text
            it.lastMessageTime = System.currentTimeMillis()
            // Увеличиваем счетчик только если сообщение НЕ от нас
            if (!isFromMe) {
                it.unreadCount += 1
            }
        }
    }

    // Функция для отметки чата как прочитанного
    fun markAsRead(chatId: String) {
        val chat = chats.find { it.id == chatId }
        chat?.let {
            it.unreadCount = 0
        }
    }

    // Функция для получения сообщений чата
    fun getMessages(chatId: String): List<Message> {
        return messages.filter { it.chatId == chatId }
    }
}