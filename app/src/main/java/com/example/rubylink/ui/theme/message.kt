package com.example.rubylink.data.model

data class Message(
    val id: String,
    val text: String,
    val sender: String,
    val timestamp: Long
)