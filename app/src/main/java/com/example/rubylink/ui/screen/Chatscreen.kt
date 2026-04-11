@file:OptIn(ExperimentalMaterial3Api::class)

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
    viewModel: ChatViewModel,
    chatId: String,
    onBackClick: () -> Unit
) {
    val messages by viewModel.messages.collectAsState()
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Чат") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Text("<")
                }
            }
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(messages.filter { it.chatId == chatId }) { msg ->
                Text("${msg.sender}: ${msg.text}")
            }
        }

        Row(modifier = Modifier.padding(8.dp)) {

            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f)
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
                Text("Send")
            }
        }
    }
}