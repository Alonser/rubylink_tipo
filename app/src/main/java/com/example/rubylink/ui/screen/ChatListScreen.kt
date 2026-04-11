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
import com.example.rubylink.ui.viewmodel.ChatViewModel

@Composable
fun ChatListScreen(
    viewModel: ChatViewModel,
    onChatClick: (String, String) -> Unit
) {
    val messages by viewModel.messages.collectAsState()

    val baseChats = listOf(
        Chat("1", "Анна Иванова", "", "", 0, 0),
        Chat("2", "Дмитрий Петров", "", "", 0, 0),
        Chat("3", "Елена Смирнова", "", "", 0, 0)
    )

    val chats = baseChats.map { chat ->
        val chatMessages = messages.filter { it.chatId == chat.id }
        val last = chatMessages.lastOrNull()

        chat.copy(
            lastMessage = last?.text ?: "",
            lastMessageTime = last?.timestamp ?: 0,
            unreadCount = chatMessages.count {
                !it.isRead && it.sender != "Me"
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        TopAppBar(
            title = { Text("Чаты", color = MaterialTheme.colorScheme.onPrimary) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        LazyColumn {
            items(chats) { chat ->
                ChatItem(chat = chat) {
                    onChatClick(chat.id, chat.userName)
                }

                HorizontalDivider(
                    modifier = Modifier.padding(start = 72.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                )
            }
        }
    }
}

@Composable
fun ChatItem(chat: Chat, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = chat.userName.take(2),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = chat.userName,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = chat.lastMessage,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }

        if (chat.unreadCount > 0) {
            Badge {
                Text(chat.unreadCount.toString())
            }
        }
    }
}