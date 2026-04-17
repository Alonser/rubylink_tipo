package com.example.rubylink.ui.screen

import androidx.compose.runtime.mutableStateListOf

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

object ChatData {
    val chats = mutableStateListOf(
        ChatInfo("1", "Анна Иванова", "Нет сообщений", System.currentTimeMillis(), 0),
        ChatInfo("2", "Дмитрий Петров", "Нет сообщений", System.currentTimeMillis(), 0),
        ChatInfo("3", "Елена Смирнова", "Нет сообщений", System.currentTimeMillis(), 0),
        ChatInfo("4", "Максим Козлов", "Нет сообщений", System.currentTimeMillis(), 0)
    )

    val messages = mutableStateListOf<Message>()

    // Добавляем тестовые сообщения
    init {
        // Сообщения для Анны (непрочитанные)
        addMessage("1", "asdwasdwasad", false)
        // Сообщения для Дмитрия (прочитанные)
        addMessage("2", "Привет!", false)
        // Сообщения для Елены (непрочитанные)
        addMessage("3", "fzxczxcdszcx", false)
        // Сообщения для Максима (прочитанные)
        addMessage("4", "Добрый день!", false)
    }

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
            it.lastMessage = text  // Всегда обновляем последнее сообщение
            it.lastMessageTime = System.currentTimeMillis()
            // Увеличиваем счетчик только если сообщение НЕ от нас
            if (!isFromMe) {
                it.unreadCount += 1
            }
        }
    }

    fun markAsRead(chatId: String) {
        val chat = chats.find { it.id == chatId }
        chat?.let {
            it.unreadCount = 0
        }
    }

    fun getMessages(chatId: String): List<Message> {
        return messages.filter { it.chatId == chatId }
    }

    // Функция для тестового сообщения от собеседника
    fun sendTestMessage(chatId: String) {
        val testMessages = listOf(
            "Привет! Как дела?", "Что нового?", "Скинь фото",
            "Понял", "Согласен", "Нет", "Да", "Может быть",
            "Отлично!", "Спасибо!", "Хорошо", "Пока!"
        )
        val randomMessage = testMessages.random()
        addMessage(chatId, randomMessage, false)
    }
}