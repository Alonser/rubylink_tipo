package com.example.rubylink.data.model

data class Message(
    val id: String,
    val text: String,
    val sender: String, // "Me" или имя собеседника
    val timestamp: Long,
    val chatId: String // ID чата
)