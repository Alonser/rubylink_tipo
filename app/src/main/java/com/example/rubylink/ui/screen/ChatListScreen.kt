package com.example.rubylink.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatListScreen(
    onChatClick: (String) -> Unit
) {
    val chats = listOf("chat1", "chat2", "chat3")

    Surface(modifier = Modifier.fillMaxSize()) { // 🔥 ВАЖНО

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {

            items(chats) { chatId ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onChatClick(chatId) }
                ) {

                    Text(
                        text = "Чат: $chatId",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurface // 🔥 ВАЖНО
                    )
                }
            }
        }
    }
}