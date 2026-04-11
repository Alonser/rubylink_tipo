package com.example.rubylink.data.model

data class Chat(
    val id: String,
    val userName: String,
    val avatarUrl: String,
    val lastMessage: String,
    val lastMessageTime: Long,
    val unreadCount: Int
)