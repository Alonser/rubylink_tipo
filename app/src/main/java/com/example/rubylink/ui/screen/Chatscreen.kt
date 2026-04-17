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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rubylink.data.model.Message
import com.example.rubylink.ui.viewmodel.ChatViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatId: String,
    onBackClick: () -> Unit,
    viewModel: ChatViewModel = viewModel()
) {
    val chatName = when(chatId) {
        "1" -> "Анна Иванова"
        "2" -> "Дмитрий Петров"
        "3" -> "Елена Смирнова"
        "4" -> "Максим Козлов"
        else -> "Чат"
    }

    val allMessages by viewModel.messages.collectAsState()
    val messages = allMessages.filter { it.chatId == chatId }
    var text by remember { mutableStateOf("") }

    LaunchedEffect(chatId) {
        if (messages.isEmpty()) {
            val welcomeMessage = when(chatId) {
                "1" -> "Привет! Как дела?"
                "2" -> "Привет!"
                "3" -> "Здравствуйте!"
                else -> "Добрый день!"
            }
            viewModel.sendMessage(welcomeMessage, chatId, false)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = chatName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "онлайн",
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
            actions = {
                IconButton(
                    onClick = {
                        viewModel.addTestMessage(chatId)
                    }
                ) {
                    Text("📨", fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        if (messages.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("💬", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Нет сообщений",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            }
        } else {
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
        }

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
                            viewModel.sendMessage(text, chatId, true)
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
fun MessageBubble(text: String, isMe: Boolean, timestamp: Long) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isMe) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant
                )
                .padding(12.dp)
        ) {
            Text(
                text = text,
                color = if (isMe) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp)),
                fontSize = 10.sp,
                color = if (isMe) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}