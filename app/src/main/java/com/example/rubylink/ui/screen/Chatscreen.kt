@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rubylink.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubylink.ui.viewmodel.ChatViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    chatId: String,
    userName: String,
    onBackClick: () -> Unit
) {
    val messages by viewModel.messages.collectAsState()
    var text by remember { mutableStateOf("") }

    // 🔥 слушаем Firebase
    LaunchedEffect(chatId) {
        viewModel.listenMessages(chatId)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text(userName) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Text("<", fontSize = 22.sp)
                }
            }
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(messages.reversed()) { msg ->
                MessageBubble(
                    text = msg.text,
                    isMe = msg.sender == "Me",
                    timestamp = msg.timestamp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Сообщение...") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        viewModel.sendMessage(text, chatId)
                        text = ""
                    }
                }
            ) {
                Text("➤")
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
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
    ) {

        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isMe) 16.dp else 0.dp,
                        bottomEnd = if (isMe) 0.dp else 16.dp
                    )
                )
                .background(
                    if (isMe)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surfaceVariant
                )
                .padding(12.dp)
                .widthIn(max = 260.dp)
        ) {

            Column {

                Text(
                    text = text,
                    color = if (isMe)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = SimpleDateFormat("HH:mm", Locale.getDefault())
                            .format(Date(timestamp)),
                        fontSize = 10.sp,
                        color = Color.Gray
                    )

                    if (isMe) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.DoneAll,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}