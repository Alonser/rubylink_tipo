package com.example.rubylink.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatId: String,
    chatName: String,
    onBackClick: () -> Unit
) {
    // Пример сообщений
    val messages = remember {
        mutableStateListOf(
            Message("1", "Привет! Как дела?", false, System.currentTimeMillis() - 3600000),
            Message("2", "Привет! Нормально, а у тебя?", true, System.currentTimeMillis() - 3500000),
            Message("3", "Тоже хорошо. Чем занимаешься?", false, System.currentTimeMillis() - 3400000),
            Message("4", "Работаю над проектом", true, System.currentTimeMillis() - 3300000),
            Message("5", "А ты?", true, System.currentTimeMillis() - 3200000),
            Message("6", "Смотрю сериал", false, System.currentTimeMillis() - 3100000),
            Message("7", "Скинь фото", true, System.currentTimeMillis() - 3000000),
            Message("8", "Вот, держи", false, System.currentTimeMillis() - 2900000)
        )
    }

    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Верхняя панель
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = chatName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "был(а) в сети недавно",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Text("←", fontSize = 24.sp, color = MaterialTheme.colorScheme.onPrimary)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        // Список сообщений
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                MessageBubble(
                    text = message.text,
                    isMe = message.isFromMe,
                    timestamp = message.timestamp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Поле ввода
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 8.dp,
            tonalElevation = 3.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Сообщение...") },
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (text.isNotBlank()) {
                            messages.add(
                                0,
                                Message(
                                    id = System.currentTimeMillis().toString(),
                                    text = text,
                                    isFromMe = true,
                                    timestamp = System.currentTimeMillis()
                                )
                            )
                            text = ""
                        }
                    },
                    shape = RoundedCornerShape(24.dp),
                    enabled = text.isNotBlank()
                ) {
                    Text("➤", fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun MessageBubble(
    text: String,
    isMe: Boolean,
    timestamp: Long
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isMe)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surfaceVariant
                )
                .padding(12.dp)
        ) {
            // Текст сообщения
            Text(
                text = text,
                color = if (isMe)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Время под сообщением
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp)),
                    fontSize = 10.sp,
                    color = if (isMe)
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

data class Message(
    val id: String,
    val text: String,
    val isFromMe: Boolean,
    val timestamp: Long
)