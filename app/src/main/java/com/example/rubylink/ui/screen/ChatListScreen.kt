@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.rubylink.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rubylink.data.model.Chat

@Composable
fun ChatListScreen(
    onChatClick: (String) -> Unit
) {
    val chats = remember {
        listOf(
            Chat("1", "Анна Иванова", "", "Привет!", System.currentTimeMillis(), 2),
            Chat("2", "Дмитрий Петров", "", "Скинь фото", System.currentTimeMillis(), 0),
            Chat("3", "Елена Смирнова", "", "Ок 👍", System.currentTimeMillis(), 1)
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Чаты") }
        )

        LazyColumn {
            items(chats) { chat ->
                ChatItem(chat = chat, onClick = {
                    onChatClick(chat.id)
                })

                HorizontalDivider(modifier = Modifier.padding(start = 72.dp))
            }
        }
    }
}

@Composable
fun ChatItem(
    chat: Chat,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(chat.userName.take(2))
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.userName,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = chat.lastMessage,
                maxLines = 1
            )
        }

        if (chat.unreadCount > 0) {
            Badge {
                Text(chat.unreadCount.toString())
            }
        }
    }
}