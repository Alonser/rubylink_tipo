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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubylink.data.model.Chat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatListScreen(
    onChatClick: (String) -> Unit
) {

    val chats = remember {
        mutableStateListOf(
            Chat("1", "Анна Иванова", "Привет!", System.currentTimeMillis(), 2),
            Chat("2", "Дмитрий Петров", "Как дела?", System.currentTimeMillis(), 0),
            Chat("3", "Елена Смирнова", "Спасибо!", System.currentTimeMillis(), 1),
            Chat("4", "Максим Козлов", "Добрый день!", System.currentTimeMillis(), 3)
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(title = { Text("Личные чаты") })

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp)
        ) {

            items(chats) { chat ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            chat.unreadCount = 0 // 🔥 СБРОС
                            onChatClick(chat.id)
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // 👤 Аватар
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = chat.userName.take(2),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {

                        Text(
                            text = chat.userName,
                            fontSize = 16.sp,
                            fontWeight = if (chat.unreadCount > 0)
                                androidx.compose.ui.text.font.FontWeight.Bold
                            else
                                androidx.compose.ui.text.font.FontWeight.Normal
                        )

                        Text(
                            text = chat.lastMessage,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {

                        Text(
                            text = formatTime(chat.lastMessageTime),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )

                        if (chat.unreadCount > 0) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        shape = CircleShape
                                    )
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = chat.unreadCount.toString(),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

                Divider()
            }
        }
    }
}

fun formatTime(time: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date(time))
}