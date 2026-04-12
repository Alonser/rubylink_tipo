package com.example.rubylink.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rubylink.ui.viewmodel.ChatViewModel

@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel,
    chatId: String
) {
    val messages by chatViewModel.messages.collectAsState()
    var text by remember { mutableStateOf("") }

    val currentUser = "Me"

    LaunchedEffect(chatId) {
        chatViewModel.listenMessages(chatId)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(messages) { msg ->
                Text(
                    text = msg.text,
                    color = if (msg.senderId == currentUser)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface
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
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (text.isNotBlank()) {
                    chatViewModel.sendMessage(text, chatId, currentUser)
                    text = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}