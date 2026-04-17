package com.example.rubylink.data.model

data class Message(
    val id: String,
    val text: String,
    val isFromMe: Boolean,
    val timestamp: Long,
    val chatId: String
)