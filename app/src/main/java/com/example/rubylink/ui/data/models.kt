package com.example.rubylink.ui.data

data class Message(
    val text: String,
    val isSentByUser: Boolean
)

data class ChatUser(
    val name: String
)