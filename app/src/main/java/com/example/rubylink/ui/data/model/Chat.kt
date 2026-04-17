package com.example.rubylink.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Chat(
    val id: String,
    val userName: String,
    val lastMessage: String,
    val lastMessageTime: Long,
    var unreadCount: Int = 0
)