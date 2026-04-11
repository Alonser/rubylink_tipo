package com.example.rubylink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rubylink.ui.screen.ChatListScreen
import com.example.rubylink.ui.screen.ChatScreen
import com.example.rubylink.ui.theme.RubyTheme
import com.example.rubylink.ui.viewmodel.ChatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RubyTheme {
                ChatApp()
            }
        }
    }
}

@Composable
fun ChatApp() {
    var selectedChatId by remember { mutableStateOf<String?>(null) }
    val viewModel: ChatViewModel = viewModel()

    if (selectedChatId == null) {
        ChatListScreen(
            onChatClick = { selectedChatId = it },
            onBackClick = {}
        )
    } else {
        ChatScreen(
            viewModel = viewModel,
            chatId = selectedChatId!!,
            onBackClick = { selectedChatId = null }
        )
    }
}