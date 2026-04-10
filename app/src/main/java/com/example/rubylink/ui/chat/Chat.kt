package com.example.rubylink.ui.chat

data class Chat(
    val id: String,
    val userName: String,
    val userAvatar: String, // URL или ресурс аватара
    val lastMessage: String,
    val lastMessageTime: Long,
    val unreadCount: Int
)